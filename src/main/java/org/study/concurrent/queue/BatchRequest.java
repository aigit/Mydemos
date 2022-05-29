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

    private static ArrayBlockingQueue<RequestPromise> blockingQueue = new ArrayBlockingQueue(200);



    public static void main(String[] args) throws InterruptedException {

        final ExecutorService executorService = Executors.newFixedThreadPool(12);
        AtomicInteger total = new AtomicInteger(60);

        final List<Future<RequestPromise>> allResultFutureList = new ArrayList<>();

        executorService.execute(()->{
            List<RequestPromise> promiseList = new ArrayList<>();
            while (true){
                /**
                 * 退化为消费队列
                 */
                final int queueSize = blockingQueue.size();
                if(queueSize>0 && queueSize<=20){
                    RequestPromise p = null;
                    try {
                        p = blockingQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(total.get()>=p.getRequest().getWantCount()){
                        total.addAndGet(-p.getRequest().getWantCount());
                        Result result = new Result(true,"queue单个抢单成功");
                        p.setResult(result);
                    }else {
                        Result result = new Result(false,"queue单个抢单失败");
                        p.setResult(result);
                    }
                    allResultFutureList.add(executorService.submit(p));
                    continue;
                }
                log.info("队列中的任务数:{}",queueSize);
                /*
                  加入批量列表
                 */
                try {
                    promiseList.add(blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int sum = promiseList.stream().mapToInt(p->p.request.getWantCount()).sum();
                log.info("sum:{},total:{},listSize:{}",sum,total,promiseList.size());
                /**
                 * 批量消费 并控制每批的数量为20
                 * 库存分三种情况
                 * 1.库存大于订单商品量
                 * 2.有库存但小于订单商品量
                 * 3.无库存
                 */
                if(promiseList.size()>=20){
                    /*
                      库存充足
                     */
                    if(total.get() >=sum){
                        try {
                            total.addAndGet(-sum);
                            promiseList.forEach(p->{
                                Result result = new Result(true,"一块儿抢单成功(批量处理)");
                                p.setResult(result);
                            });
                            allResultFutureList.addAll(executorService.invokeAll(promiseList));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        promiseList.clear();
                        continue;
                    }
                    /*
                      无库存
                     */
                    if(total.get()<=0){
                        try {
                            promiseList.forEach(p->{
                                Result result = new Result(false,"抱歉，库存已被抢完(批量处理)");
                                p.setResult(result);
                            });
                            allResultFutureList.addAll(executorService.invokeAll(promiseList));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        promiseList.clear();
                        continue;
                    }

                    /*
                      库存紧张，退化为单个消费
                     */
                    if(total.get()<sum && total.get()>0){
                        promiseList.forEach(p->{
                            if(total.get()>=p.getRequest().getWantCount()){
                                total.addAndGet(-p.getRequest().getWantCount());
                                Result result = new Result(true,"单个抢单成功");
                                p.setResult(result);
                            }else {
                                Result result = new Result(false,"单个抢单失败");
                                p.setResult(result);
                            }
                            allResultFutureList.add(executorService.submit(p));
                        });
                        promiseList.clear();
                        continue;
                    }

                }
            }
        });

        for (int i = 1; i < 122; i++) {
            UserRequest userRequest = new UserRequest(1,String.valueOf(i),String.valueOf("order"+i));
            RequestPromise requestPromise = new RequestPromise(userRequest,null);
            blockingQueue.offer(requestPromise);
        }


        TimeUnit.SECONDS.sleep(5L);//休息5秒后拿结果

        allResultFutureList.forEach(f->{
            try {
                final RequestPromise callResult = f.get(200,TimeUnit.MILLISECONDS);
                log.info("{}抢到了吗:{}",callResult.getRequest(), callResult.getResult());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }


    @Data
    @ToString
    static class RequestPromise implements Callable<RequestPromise> {

        private UserRequest request;
        private Result result;

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
