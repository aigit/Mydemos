package org.study.jvm;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class InstanceTest {

    private int a= 4;

    public static void main(String[] args) {
        try {
            InstanceTest ins = InstanceTest.class.newInstance();
            final int a = ins.getA();
            log.info("get a:{}",a);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private InstanceTest(){

    }

    public int getA() {
        return a;
    }
}
