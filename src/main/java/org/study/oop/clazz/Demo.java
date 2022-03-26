package org.study.oop.clazz;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class Demo {
    public static void main(String[] args) {
        Animal a = new Dog();
        //Cat cat = (Cat) a;
        Dog dog = (Dog) a;
        dog.speak();
        log.info("是否相同:{}",Objects.equals(a,dog));
    }
}
