package org.study.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.study.MyApplication;
import org.study.concurrent.locks.ReentrantLockTest;
import sun.misc.Unsafe;

@Slf4j
@SpringBootTest(classes = Test.class)
public class Test {

    @org.junit.jupiter.api.Test
    public void testReentrantLock(){
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        reentrantLockTest.test();
    }

}
