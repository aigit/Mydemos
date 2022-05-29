package org.study.concurrent.queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class BatchRequest {

    /**
     * 缓存队列
     */
    private static ArrayBlockingQueue<RequestPromise> BLOCKING_QUEUE = new ArrayBlockingQueue(200);

    private static final int BATCH_NUMBER = 20;// 每批合并处理数量


    public static void main(String[] args) throws InterruptedException {

        final ExecutorService executorService = Executors.newFixedThreadPool(12);
        AtomicInteger totalStock = new AtomicInteger(60);//库存量

        List<RequestPromise> requestList = new ArrayList<>();
        /*
            模拟122个请求
         */
        for (int i = 1; i < 122; i++) {
            int j = i;
            executorService.submit(()->{
                UserRequest userRequest = new UserRequest(1,String.valueOf(j),String.valueOf("order"+j));
                RequestPromise requestPromise = new RequestPromise(userRequest,null);
                BLOCKING_QUEUE.offer(requestPromise);
                requestList.add(requestPromise);
            });
        }

        TimeUnit.SECONDS.sleep(5L);//休息5秒后拿结果

        executorService.execute(()->{
            List<RequestPromise> promiseList = new ArrayList<>();
            while (true){
                /**
                 * 退化为消费队列
                 */
                final int queueSize = BLOCKING_QUEUE.size();
                if(queueSize>0 && queueSize<=BATCH_NUMBER){
                    RequestPromise p = null;
                    try {
                        p = BLOCKING_QUEUE.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    eachTaskHandler(p,totalStock,executorService);
                    continue;
                }
                //log.info("队列中的任务数:{}",queueSize);
                /*
                  加入批量列表
                 */
                try {
                    promiseList.add(BLOCKING_QUEUE.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                /*
                 不足一批退化为单个处理
                 */
                /*if(promiseList.size()>0 && promiseList.size()<20){
                    promiseList.forEach(p->{
                        eachTaskHandler(p,totalStock,executorService);
                    });
                    promiseList.clear();
                    continue;
                }*/

                /**
                 * 批量消费 并控制每批的数量为BATCH_NUMBER
                 * 库存分三种情况
                 * 1.库存大于订单商品量
                 * 2.有库存但小于订单商品量
                 * 3.无库存
                 */
                if(promiseList.size()>=BATCH_NUMBER){
                    int sum = promiseList.stream().mapToInt(p->p.request.getWantCount()).sum();
                    /*
                      库存充足
                     */
                    if(totalStock.get() >=sum){
                        totalStock.addAndGet(-sum);
                        promiseList.forEach(p->{
                            Result result = new Result(true,"一块儿抢单成功(批量处理)");
                            p.setResult(result);
                            final Future<RequestPromise> future = executorService.submit(p);
                            p.setFuture(future);
                        });
                        promiseList.clear();
                        continue;
                    }
                    /*
                      无库存
                     */
                    if(totalStock.get()<=0){
                        promiseList.forEach(p->{
                            Result result = new Result(false,"抱歉，库存已被抢完(批量处理)");
                            p.setResult(result);
                            final Future<RequestPromise> future = executorService.submit(p);
                            p.setFuture(future);
                        });
                        promiseList.clear();
                        continue;
                    }

                    /*
                      库存紧张，退化为单个消费
                     */
                    if(totalStock.get()<sum && totalStock.get()>0){
                        promiseList.forEach(p->{
                            eachTaskHandler(p,totalStock,executorService);
                        });
                        promiseList.clear();
                        continue;
                    }

                }
            }
        });




        TimeUnit.SECONDS.sleep(5L);//休息5秒后拿结果

        requestList.forEach(r->{
            try {
                final RequestPromise callResult = r.getFuture().get(3000,TimeUnit.MILLISECONDS);
                log.info("{}抢到了吗:{}",callResult.getRequest(), callResult.getResult());
            } catch (Exception e) {
                log.error("发生了异常:{},e:{}",r,e);
            }
        });


    }

    /**
     * 每个请求任务单独处理
     * @param p 任务
     * @param totalStock 库存数量
     * @param executorService
     */
    private static void eachTaskHandler(RequestPromise p,AtomicInteger totalStock,ExecutorService executorService){
        if(totalStock.get()>=p.getRequest().getWantCount()){
            totalStock.addAndGet(-p.getRequest().getWantCount());
            Result result = new Result(true,"单个抢单成功");
            p.setResult(result);
        }else {
            Result result = new Result(false,"单个抢单失败");
            p.setResult(result);
        }
        final Future<RequestPromise> future = executorService.submit(p);
        p.setFuture(future);
    }


    @Data
    @ToString
    static class RequestPromise implements Callable<RequestPromise> {

        private UserRequest request;
        private Result result;

        private Future<RequestPromise> future;

        public RequestPromise(UserRequest request, Result result) {
            this.request = request;
            this.result = result;
        }

        @Override
        public RequestPromise call() throws Exception {
            return this;
        }
    }


    @Data
    @ToString
    @AllArgsConstructor
    static class UserRequest {
        private int wantCount;
        private String userId;
        private String orderId;


    }

    @Data
    @ToString
    @AllArgsConstructor
    static
    class Result{
        private Boolean success;
        private String msg;

    }
}
