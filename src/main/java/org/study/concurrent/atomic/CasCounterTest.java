package org.study.concurrent.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CasCounterTest {
    private  AtomicInteger atomicInteger = new AtomicInteger();
    private int i = 0;

    public void test() {
        final CasCounterTest casCounterTest = new CasCounterTest();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    casCounterTest.count();
                    casCounterTest.safeCount();
                    //casCounterTest.atomAdd();
                }
            });
            ts.add(thread);
        }
        ts.forEach(thread -> {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        /*ts.forEach(thread ->{

        });*/

        System.out.println(casCounterTest.i);
        System.out.println(casCounterTest.atomicInteger.get());
    }

    private void safeCount(){
        for (;;){
            int i = atomicInteger.get();
            boolean suc = atomicInteger.compareAndSet(i,++i);
            if(suc){
                break;
            }
        }
        //atomicInteger.incrementAndGet();
    }

    private void atomAdd(){
        atomicInteger.incrementAndGet();
    }

    private void count(){
        i++;
    }
}
