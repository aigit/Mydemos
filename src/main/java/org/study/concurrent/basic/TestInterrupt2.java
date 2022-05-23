package org.study.concurrent.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestInterrupt2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(10L);
                    log.info("被叫醒...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("运行中...");

            }
        },"thread1");
        thread.start();

        TimeUnit.SECONDS.sleep(3L);
        thread.interrupt();
        log.info("是否打断:{}",thread.isInterrupted());

    }

}
