package org.study.concurrent.locks;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockTest {


    public static void main(String[] args) throws InterruptedException {
        ChopStick chopStick1 = new ChopStick("1");
        ChopStick chopStick2 = new ChopStick("2");
        ChopStick chopStick3 = new ChopStick("3");
        ChopStick chopStick4 = new ChopStick("4");
        ChopStick chopStick5 = new ChopStick("5");

        Zhexuejia a = new Zhexuejia("a",chopStick1,chopStick2);
        Zhexuejia b = new Zhexuejia("b",chopStick2,chopStick3);
        Zhexuejia c = new Zhexuejia("c",chopStick3,chopStick4);
        Zhexuejia d = new Zhexuejia("d",chopStick4,chopStick5);
        Zhexuejia e = new Zhexuejia("e",chopStick5,chopStick1);
        List<Zhexuejia> zhexuejiaList = new ArrayList<>();
        zhexuejiaList.add(a);
        zhexuejiaList.add(b);
        zhexuejiaList.add(c);
        zhexuejiaList.add(d);
        zhexuejiaList.add(e);

        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Void>> futures = executorService.invokeAll(zhexuejiaList);
        //futures.
    }


}

@Slf4j
class Zhexuejia implements Callable<Void> {
    String name;

    ChopStick left;
    ChopStick right;

    public Zhexuejia(String name,ChopStick left,ChopStick right){
        this.name = name;
        this.left = left;
        this.right = right;
    }


    @SneakyThrows
    private void eat() {
        log.info("{}吃面条",this);
        TimeUnit.SECONDS.sleep(1L);
    }

    @Override
    public Void call() throws Exception {
        while (true){
            if(left.tryLock()){
                try{
                    if(right.tryLock()){
                        try {
                            eat();
                        }finally {
                            right.unlock();
                        }
                    }
                }finally {
                    left.unlock();
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Zhexuejia{" +
                "name='" + name + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

class ChopStick extends ReentrantLock {
    String name;

    public ChopStick(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "ChopStick{" +
                "name='" + name + '\'' +
                '}';
    }
}