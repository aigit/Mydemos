package org.study.concurrent.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Entrance implements Runnable{

    public static Counter counter= new Counter();
    private final String id;
    private static volatile boolean canceled;
    private int num;

    public static List<Entrance> entranceList = new ArrayList<>();

    public Entrance(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!canceled){
            synchronized (this){
                ++num;
            }
            counter.add();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(id+" has been interrupt");
            }
        }
    }

    public static int getSum(){
        int sum = 0;
        for (Entrance en:Entrance.entranceList) {
            sum+=en.num;
        }
        return sum;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Entrance entrance = new Entrance(String.valueOf(i));
            Entrance.entranceList.add(entrance);
        }
        ExecutorService executors = Executors.newCachedThreadPool();
        Entrance.entranceList.forEach((en)->{executors.execute(en);});
        try {
            TimeUnit.SECONDS.sleep(30L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Entrance.canceled = true;
        executors.shutdown();
        try {
            if(!executors.awaitTermination(250L,TimeUnit.MILLISECONDS)){
                System.out.println("某些任务没有跑完");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Entrance.getSum()+"==="+Entrance.counter.getCount());
    }

}

class Counter{
    int count;

    public synchronized void add(){
        count++;
    }

    public int getCount(){
        return count;
    }
}
