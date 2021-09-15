package org.study.collection.map;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class HashMapTest {

    public void test(){
        testSize();
    }


    private void testHash(){
        HashMap map = new HashMap();
        Employee employee1 = new Employee("a",2,
                new MyDate("2000","03","21"));
        map.put(employee1,employee1.getName());
        Employee employee2 = new Employee("b",3,
                new MyDate("2001","03","21"));
        map.put(employee2,employee2.getName());
        Employee employee3 = new Employee("a",40,
                new MyDate("2000","03","21"));
        map.put(employee3,employee3.getName());

        map.forEach((k,v)->{
            log.info("k:{},v:{}",k,v);
        });
    }

    private void testSize(){
        HashMap map = new HashMap();
        for (int i = 0; i < 12; i++) {
            map.put(i,i);
        }
        log.info("map:{}",map);
        String a = "abc";
        a.intern();
    }
}
