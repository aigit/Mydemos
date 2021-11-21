package org.study.concurrent.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class MyBlockingQueue<T> {

    private Deque<T> deque = new ArrayDeque();

    private ReentrantLock lock = new ReentrantLock();
    private Condition consumLock = lock.newCondition();
    private Condition productLock = lock.newCondition();

    private int capcity;

    public MyBlockingQueue(int capcity){
        this.capcity = capcity;
    }

    /**
     * 阻塞获取
     * @return
     */
    public T take(){
        lock.lock();
        try{
            while (deque.isEmpty()){
                try {
                    consumLock.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            T t = deque.removeFirst();
            productLock.signalAll();
            return t;
        }finally {
            lock.unlock();
        }
    }

    public void offer(T t){
        lock.lock();
        try{
            while (deque.size()>=capcity){
                try {
                    productLock.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            deque.addLast(t);
            consumLock.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public Deque<T> getDeque() {
        return deque;
    }
}
