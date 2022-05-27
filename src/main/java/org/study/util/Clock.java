package org.study.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Clock {

    private Long startTime = System.currentTimeMillis();

    public void getDuration(){
        log.info("经过了:{}",System.currentTimeMillis()-startTime);
    }

}
