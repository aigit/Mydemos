package org.study.concurrent.parallel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecursiveTest {

    public void test(){
        long beginTime = System.currentTimeMillis();
        int result = compute(1000_0000);
        log.info("计算结果:{},耗时:{}",result,System.currentTimeMillis()-beginTime);
    }

    private int compute(Integer n){
        if(n==1){
            return 1;
        }else {
            return compute(n-1)+n;
        }
    }
}

