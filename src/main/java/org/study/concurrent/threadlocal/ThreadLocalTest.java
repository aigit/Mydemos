package org.study.concurrent.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadLocalTest {

    private static ThreadLocal<Integer> countLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        countLocal.set(57);
        Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        log.info("拿到的值:{}",countLocal.get());
                        countLocal.set(56);

                    }
                }, "sub thread");
        thread.start();
        TimeUnit.SECONDS.sleep(3);
        log.info("拿到的值:{}",countLocal.get());
    }

}
