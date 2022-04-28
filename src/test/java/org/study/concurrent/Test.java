package org.study.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.study.MyApplication;
import org.study.concurrent.locks.ReentrantLockTest;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Slf4j
@SpringBootTest(classes = MyApplication.class)
public class Test {

    @Resource(name = "redissonConnectionFactory")
    private RedissonConnectionFactory redissonConnectionFactory;

    @org.junit.jupiter.api.Test
    public void testReentrantLock(){
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        reentrantLockTest.test();
    }

    @org.junit.jupiter.api.Test
    public void testBitMap(){
        final RedisConnection connection = redissonConnectionFactory.getConnection();
        byte[] bitKey = "bk".getBytes(StandardCharsets.UTF_8);
        connection.setBit(bitKey,0,true);
        connection.setBit(bitKey,1,true);
        connection.setBit(bitKey,2,false);
        connection.setBit(bitKey,3,true);

        log.info("Bit count:{}",connection.bitCount(bitKey));
    }

}
