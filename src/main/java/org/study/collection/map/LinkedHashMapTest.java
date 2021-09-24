package org.study.collection.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {

    public void test(){
        LinkedHashMap<Integer,String> map = new LinkedHashMap();
        map.put(1,"a");
        map.put(2,"b");
        map.entrySet().forEach(e->{
            Map.Entry entry = e;
        });
    }
}
