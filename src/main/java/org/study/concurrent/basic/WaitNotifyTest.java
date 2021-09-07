package org.study.concurrent.basic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitNotifyTest {
    private static Object lock = new Object();
    private WaitSignal waitSignal = new WaitSignal(1,10);

    public void loopPrint(){
        for (int i = 0; i < waitSignal.getLoopNum(); i++) {
            print();
        }
    }

    void print(){
        new Thread(()->{
            synchronized (lock){
                while (waitSignal.getSig()!=1){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("a");
                waitSignal.setSig(2);
                lock.notifyAll();
            }
        },"A").start();

        new Thread(()->{
            synchronized (lock){
                while (waitSignal.getSig()!=2){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("b");
                waitSignal.setSig(3);
                lock.notifyAll();
            }
        },"B").start();

        new Thread(()->{
            synchronized (lock){
                while (waitSignal.getSig()!=3){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("c");
                waitSignal.setSig(1);
                lock.notifyAll();
            }
        },"C").start();
    }

}


class WaitSignal{
    private int sig;
    private int loopNum;

    public WaitSignal(int sig, int loopNum) {
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