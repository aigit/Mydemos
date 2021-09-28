package org.study.concurrent.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockPrint {

    private LockSignal lockSignal = new LockSignal(1,10);
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition conditiona = reentrantLock.newCondition();
    private Condition conditionb = reentrantLock.newCondition();
    private Condition conditionc = reentrantLock.newCondition();

    public void loopPrint(){
        for (int i = 0; i < lockSignal.getLoopNum(); i++) {
            print();
        }
    }

    void print(){
        new Thread(()->{
            reentrantLock.lock();
            try{
                while (lockSignal.getSig()!=1){
                    try {
                        conditiona.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("a");
                lockSignal.setSig(2);
                conditionb.signal();
            }finally {
                reentrantLock.unlock();
            }
        },"A").start();

        new Thread(()->{
            reentrantLock.lock();
            try{
                while (lockSignal.getSig()!=2){
                    try {
                        conditionb.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("b");
                lockSignal.setSig(3);
                conditionc.signal();
            }finally {
                reentrantLock.unlock();
            }
        },"B").start();

        new Thread(()->{
            reentrantLock.lock();
            try{
                while (lockSignal.getSig()!=3){
                    try {
                        conditionc.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("c");
                lockSignal.setSig(1);
                conditiona.signal();
            }finally {
                reentrantLock.unlock();
            }
        },"C").start();
    }

}

class LockSignal{
    private int sig;
    private int loopNum;

    public LockSignal(int sig, int loopNum) {
        this.sig = sig;
        this.loopNum = loopNum;
    }


    public int getSig() {
        return sig;
    }

    public int getLoopNum() {
        return loopNum;
    }

    public void setSig(int sig) {
        this.sig = sig;
    }
}
