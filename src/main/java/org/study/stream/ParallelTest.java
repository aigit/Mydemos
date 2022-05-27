package org.study.stream;

import lombok.extern.slf4j.Slf4j;
import org.study.util.Clock;

import java.util.concurrent.TimeUnit;


@Slf4j
public class ParallelTest {



    public static void main(String[] args) throws InterruptedException {
        Clock clock = new Clock();

        TimeUnit.SECONDS.sleep(3);

        clock.getDuration();

    }

}
