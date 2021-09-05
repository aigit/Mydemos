package org.study;

import lombok.extern.slf4j.Slf4j;
import org.study.concurrent.TestInterrupt;

import java.util.concurrent.TimeUnit;

@Slf4j
public class MyApplication {

    public static void main(String[] args) {
        TestInterrupt testInterrupt = new TestInterrupt();
        testInterrupt.monitor();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testInterrupt.interupts();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
