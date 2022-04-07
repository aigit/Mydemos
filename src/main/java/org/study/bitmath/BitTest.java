package org.study.bitmath;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BitTest {

    public static void main(String[] args) {
        BitTest bitTest = new BitTest();
        bitTest.test();
    }

    public void test(){
        int hash = "world".hashCode();
        log.info("hash:{}",hash);
        log.info("before move:{}",Integer.toBinaryString(hash));
        log.info("moved:{}", hash>>>16);
        log.info("moved bit:{}", Integer.toBinaryString(hash>>>16));

        int a = 14,b=15;
        a=a^b;
        b=a^b;
        a=a^b;
        log.info("a={},b={}",a,b);
        long timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        log.info("31位时间戳:{}",timestamp);
        log.info("31位:{},数字：{}",Integer.toBinaryString ((1<<31)|2),timestamp);
        log.info("无符号:{}",timestamp<<32|1);
    }
}
