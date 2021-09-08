package org.study.concurrent.atomic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LamdaAtomicTest {

    public void test(){

        demo(()->new AtomicIntegerArray(10),
                (arr)->arr.length(),
                (arr,index)-> arr.getAndIncrement(index),
                (t)->System.out.println(t));
    }

    private static <T> void demo(
            Supplier<T> supplier, Function<T,Integer> function,
            BiConsumer<T,Integer> putConsumer, Consumer<T> printConsumer
            ){
        T t = supplier.get();
        Integer length = function.apply(t);
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            threadList.add(new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    putConsumer.accept(t,j%length);
                }
            },String.valueOf(i)));
        }

        threadList.forEach((th)->{th.start(); });
        threadList.forEach((th)->{
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        printConsumer.accept(t);
    }
}
