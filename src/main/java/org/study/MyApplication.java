package org.study;

import lombok.extern.slf4j.Slf4j;
import org.study.collection.map.HashMapTest;
import org.study.concurrent.atomic.LamdaAtomicTest;
import org.study.concurrent.basic.ParkPrintTest;
import org.study.concurrent.basic.ReentrantLockPrint;
import org.study.concurrent.basic.TestInterrupt;
import org.study.concurrent.basic.WaitNotifyTest;
import org.study.concurrent.locks.CountDownLatchTest;
import org.study.concurrent.locks.ReentrantLockTest;
import org.study.concurrent.parallel.ForkJoinTest;
import org.study.concurrent.parallel.RecursiveTest;
import org.study.concurrent.singleon.InnerclassSingle;
import org.study.jvm.CglibOOMTest;
import org.study.jvm.SoftReferenceTest;
import org.study.jvm.WeekReferenceTest;
import org.study.str.StringTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Deque;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class MyApplication {

    public static void main(String[] args) throws IOException, InterruptedException {

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

        /*LamdaAtomicTest lamdaAtomicTest = new LamdaAtomicTest();
        lamdaAtomicTest.test();*/

        /*ForkJoinTest forkJoinTest = new ForkJoinTest();
        RecursiveTest recursiveTest = new RecursiveTest();
        forkJoinTest.test();
        recursiveTest.test();*/

        /*CountDownLatchTest countDownLatchTest = new CountDownLatchTest();
        countDownLatchTest.test();*/
        /*HashMapTest hashMapTest = new HashMapTest();
        hashMapTest.test();*/



        /*StringTest stringTest = new StringTest();
        stringTest.test();*/

        /*CglibOOMTest cglibOOMTest = new CglibOOMTest();
        cglibOOMTest.test();*/
        /*SoftReferenceTest softReferenceTest = new SoftReferenceTest();
        softReferenceTest.test();*/

        /*WeekReferenceTest weekReferenceTest = new WeekReferenceTest();
        weekReferenceTest.test();
        Thread.currentThread().getContextClassLoader();*/


        //
        String s4 = "22";
        String s3 = new String("2")+new String("2");
        s3.intern();
        System.out.println(s3==s4);
        byte[] bytes5 = "5".getBytes();
        byte b = 0;
        for (int i = 0; i < bytes5.length; i++) {
           b = (byte) (b^bytes5[i]);
        }
        System.out.println(b);
        byte[] bytes8 = "8".getBytes();
        System.out.println(bytes5);

    }
}
