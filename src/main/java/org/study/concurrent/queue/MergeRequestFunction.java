package org.study.concurrent.queue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

@FunctionalInterface
public interface MergeRequestFunction{

    void mergeRequest(ArrayBlockingQueue<BatchRequest.RequestPromise> queue,
                      int batchNum, ExecutorService executorService,
                      CountDownLatch countDownLatch);

}
