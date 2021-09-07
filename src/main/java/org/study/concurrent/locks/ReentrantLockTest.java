package org.study.concurrent.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockTest {

    private  Boolean hasWaimai = false;
    private  ReentrantLock lock = new ReentrantLock();
    private  Condition condition = lock.newCondition();

    public void doWork(){
        if(lock.tryLock()){
            try{
                while (!hasWaimai){
                    try {
                        log.info("没有外卖不干活");
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("吃了外卖才能干活");
                }
            }finally {
                lock.unlock();
            }
        }


    }

   public void test(){
        Thread doworkThread = new Thread(()->{
            doWork();
        },"小女的工作");
        doworkThread.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            lock.lock();
            try{
                hasWaimai = true;
                condition.signal();
            }finally {
                lock.unlock();
            }
        },"送外卖的到了").start();

    }

}
