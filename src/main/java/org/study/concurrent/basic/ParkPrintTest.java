package org.study.concurrent.basic;

import java.util.concurrent.locks.LockSupport;

public class ParkPrintTest {
    static Thread threada;
    static Thread threadb;
    static Thread threadc;

    public void print() {
        Printer pr = new Printer();
        threada = new Thread(()->{
            pr.print("a",1,threadb);
        }, "A");

        threadb = new Thread(()->{
            pr.print("b",2,threadc);
        }, "A");

        threadc = new Thread(()->{
            pr.print("c",3,threada);
        }, "A");
        threada.start();
        threadb.start();
        threadc.start();
        LockSupport.unpark(threada);
    }
}


class Printer{

    private String printerContent;
    private int sig;
    private Thread nextThread;
    private Thread workThread;


    public Printer(){}

    public void print(String printerContent, int sig, Thread nextThread){
        for (int i = 0; i < 10; i++) {
            LockSupport.park();
            System.out.print(printerContent);
            LockSupport.unpark(nextThread);
        }
    }
}
