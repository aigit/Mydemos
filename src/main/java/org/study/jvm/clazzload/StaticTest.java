package org.study.jvm.clazzload;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StaticTest {

    static {
        a = 3;
        log.info("先初始化静态代码块");
    }

    private static int a = 2;

    public static void main(String[] args) {
        log.info("a=={}",a);
    }
}
