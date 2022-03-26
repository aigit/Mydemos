package org.study.oop.clazz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Dog extends Animal{
    @Override
    void speak() {
        log.info("汪汪");
    }
}
