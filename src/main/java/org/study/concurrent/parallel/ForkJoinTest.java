package org.study.concurrent.parallel;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class ForkJoinTest {

    public void test(){
        ForkJoinPool forkJoinPool = new ForkJoinPool(12);
        MyForkJoin myForkJoin = new MyForkJoin(1,1000_0000);
        long beginTime = System.currentTimeMillis();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myForkJoin);
        try {

            log.info("计算结果:{},耗时:{}",submit.get(),System.currentTimeMillis()-beginTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }




}

class MyForkJoin extends RecursiveTask<Integer>{

    private int begin;
    private int end;

    public MyForkJoin(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        if(begin==end){
            return begin;
        }
        if((end-begin)==1){
            return end+begin;
        }

        int mid = (begin+end)/2;
        MyForkJoin myForkJoinLeft = new MyForkJoin(begin,mid);
        myForkJoinLeft.fork();
        MyForkJoin myForkJoinRight = new MyForkJoin(mid+1,end);
        myForkJoinRight.fork();

        int result = myForkJoinLeft.join()+myForkJoinRight.join();

        return result;
    }
}