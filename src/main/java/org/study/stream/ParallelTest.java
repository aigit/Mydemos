package org.study.stream;

import lombok.extern.slf4j.Slf4j;
import org.study.util.Clock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


@Slf4j
public class ParallelTest {

    private static ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Clock clock = new Clock();

        TimeUnit.SECONDS.sleep(3);

        clock.getDuration();


    }

}
