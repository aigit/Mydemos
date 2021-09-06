package org.study.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

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
        LockSupport.park();
    }

    public void interupts(){
        thread.interrupt();
    }

}
