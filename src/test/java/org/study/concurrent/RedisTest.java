package org.study.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.ScoredEntry;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.study.MyApplication;

import javax.annotation.Resource;
import java.util.Collection;

@Slf4j
@SpringBootTest(classes = MyApplication.class)
public class RedisTest {


    @Resource(name = "redissonConnectionFactory")
    private RedissonConnectionFactory redissonConnectionFactory;

    @Autowired
    private RedissonClient redissonClient;


    @Test
    public void zSetTest(){
        final RedisConnection connection = redissonConnectionFactory.getConnection();
        final RScoredSortedSet<String> aset = redissonClient.getScoredSortedSet("aset");
        aset.add(1D,"A");
        aset.add(2D,"b");
        aset.add(3D,"c");
        aset.add(4D,"d");
        final Collection<String> strings = aset.valueRange(0, -1);
        strings.forEach(System.out::println);
        final Collection<ScoredEntry<String>> scoredEntries = aset.entryRangeReversed(2D, true, 4D, false);
        scoredEntries.forEach(System.out::println);
    }

    @Test
    public void zSetTestAdd(){
        final RedisConnection connection = redissonConnectionFactory.getConnection();
        final RScoredSortedSet<String> aset = redissonClient.getScoredSortedSet("aset");
        aset.add(5D,"e");
        aset.add(6D,"f");
        aset.add(7D,"g");
        aset.add(8D,"h");
        final Collection<String> strings = aset.valueRange(0, -1);
        strings.forEach(System.out::println);

    }

}
