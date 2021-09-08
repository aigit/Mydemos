package org.study.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    public void mock(){
        AtomicInteger atomicInteger = new AtomicInteger(2);
        atomicInteger.incrementAndGet();
    }

}
