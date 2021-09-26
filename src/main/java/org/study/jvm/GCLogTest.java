package org.study.jvm;

import java.util.concurrent.TimeUnit;

public class GCLogTest {


    private static int one = 1*1024*1024;

    public void allocate(){
        byte[] _1,_2,_3,_4;
        _1 = new byte[one*2];
        _2 = new byte[one*2];
        _3 = new byte[one*2];
        _4 = new byte[one*2];


        Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
    }


}
