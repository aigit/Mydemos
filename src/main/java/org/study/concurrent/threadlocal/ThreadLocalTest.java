package org.study.concurrent.threadlocal;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

    private static ThreadLocal<Integer> countLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        countLocal.set(57);
        Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("拿到的值"+countLocal.get());
                        countLocal.set(56);

                    }
                }, "sub thread");
        thread.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("拿到的值"+countLocal.get());
    }

}
