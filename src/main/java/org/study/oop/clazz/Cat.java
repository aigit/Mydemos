package org.study.oop.clazz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cat extends Animal{
    @Override
    void speak() {
        log.info("喵喵");
    }
}
