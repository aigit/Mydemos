package org.study.bitmath;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BitTest {

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
    }
}
