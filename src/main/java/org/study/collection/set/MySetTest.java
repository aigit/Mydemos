package org.study.collection.set;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MySetTest {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        final String collect = set.stream().collect(Collectors.joining(","));
        System.out.println(collect);
    }
}
