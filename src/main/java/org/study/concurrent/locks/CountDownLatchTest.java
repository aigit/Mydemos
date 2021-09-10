package org.study.concurrent.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchTest {

    public void test(){
        loadWangzhe();
    }

    private void loadWangzhe(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        String[] processArr = new String[10];
        Random random = new Random();

        for (int j = 0; j < 10; j++) {
            int playi = j;
            executorService.submit(()->{
                for (int i = 0; i < 100; i++) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    processArr[playi]=i+"%";
                    System.out.print("\r"+ Arrays.toString(processArr));
                }
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
            //log.info("\r"+ Arrays.toString(processArr));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("\rAll ready!");
        executorService.shutdown();

    }

}
