package org.study.concurrent.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestInterrupt {

    private static Thread thread;

    public void monitor(){
        thread = new Thread(()->{
            while (true){
                if(thread.isInterrupted()){
                    log.info("处理后世");
                    break;
                }
                try{
                    log.info("监控任务");
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException e){
                    thread.interrupt();
                }
            }
        },"monitor");

        thread.start();
    }

    public void interupts(){
        thread.interrupt();
    }

    public void test(){
        TestInterrupt testInterrupt = new TestInterrupt();
        testInterrupt.monitor();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testInterrupt.interupts();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
