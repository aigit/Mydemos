package org.study.collection.set;

import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class MySetTest {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        final String collect = set.stream().collect(Collectors.joining(","));
        System.out.println(collect);

        Set<Integer> iset = Stream.of(4).collect(Collectors.toSet());
        log.info("数字包含:{}",iset.contains(4));

        Future future = new CompletableFuture();
    }
}
