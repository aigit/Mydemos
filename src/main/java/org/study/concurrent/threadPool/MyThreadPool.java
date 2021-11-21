package org.study.concurrent.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MyThreadPool {

    public static void main(String[] args) {
        MyThreadPool threadPool = new MyThreadPool(10,30,TimeUnit.MILLISECONDS,50);
        for (int i = 1; i <=700; i++) {
            final int j = i;
            threadPool.execute(()->{
                log.info("thread i:{}",j);
            });
        }
    }

    private MyBlockingQueue<Runnable> taskQueue;

    private HashSet<Work> works = new HashSet<>();

    public MyThreadPool(int coreSize, long timeout, TimeUnit timeUnit,int queueSize) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        taskQueue = new MyBlockingQueue<>(queueSize);
    }

    private int coreSize;
    private long timeout;
    private TimeUnit timeUnit;

    public MyThreadPool(){

    }

    public void execute(Runnable runnable){
        synchronized (works){

            if(works.size()<coreSize){
                Work work = new Work(runnable);
                works.add(work);
                work.start();
            }else {
                taskQueue.offer(runnable);
                log.info("queue size:{},works size:{}",taskQueue.getDeque().size(),works.size());
            }

        }
    }

    class Work extends Thread{

        private Runnable runnable;

        public Work(Runnable runnable,String name){
            this.runnable = runnable;
        }

        public Work(Runnable runnable){
            this.runnable = runnable;
        }

        @Override
        public void run() {
            while (runnable!=null || (runnable= taskQueue.take())!=null){
                try{
                    runnable.run();
                    //taskQueue.offer(runnable);
                } catch (Exception e){
                    e.printStackTrace();
                }finally {
                    runnable = null;
                }
            }
            works.remove(this);

        }
    }
}
