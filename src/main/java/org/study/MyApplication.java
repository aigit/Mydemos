package org.study;

import lombok.extern.slf4j.Slf4j;
import org.study.concurrent.atomic.LamdaAtomicTest;
import org.study.concurrent.basic.ParkPrintTest;
import org.study.concurrent.basic.ReentrantLockPrint;
import org.study.concurrent.basic.TestInterrupt;
import org.study.concurrent.basic.WaitNotifyTest;
import org.study.concurrent.locks.ReentrantLockTest;
import org.study.concurrent.singleon.InnerclassSingle;

@Slf4j
public class MyApplication {

    public static void main(String[] args) {

        /*TestInterrupt testInterrupt  = new TestInterrupt();
        testInterrupt.test();*/

        /*ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        reentrantLockTest.test();*/

        /*WaitNotifyTest waitNotifyTest = new WaitNotifyTest();
        waitNotifyTest.loopPrint();*/

        /*ReentrantLockPrint reentrantLockPrint = new ReentrantLockPrint();
        reentrantLockPrint.loopPrint();*/

        /*ParkPrintTest parkPrintTest = new ParkPrintTest();
        parkPrintTest.print();*/
        /*InnerclassSingle instance = InnerclassSingle.getInstance();
        log.info("获取到的单例:{}",instance);*/

        LamdaAtomicTest lamdaAtomicTest = new LamdaAtomicTest();
        lamdaAtomicTest.test();

    }
}
