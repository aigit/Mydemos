package org.study.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.RedisConnection;
import org.study.MyApplication;
import org.study.concurrent.locks.ReentrantLockTest;
import org.study.dto.Category;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@SpringBootTest(classes = MyApplication.class)
public class Test {

    @Resource(name = "redissonConnectionFactory")
    private RedissonConnectionFactory redissonConnectionFactory;

    @Autowired
    private RedissonClient redissonClient;

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
        connection.setBit(bitKey,4,true);
        connection.setBit(bitKey,5,true);
        connection.setBit(bitKey,6,false);

        log.info("Bit count:{}",connection.bitCount(bitKey));
        /*final BitFieldSubCommands.BitFieldGet bitFieldGet = BitFieldSubCommands.
                BitFieldGet.create(BitFieldSubCommands.BitFieldType.INT_32, BitFieldSubCommands.Offset.offset(4));*/
        BitFieldSubCommands.BitFieldGetBuilder fieldGetBuilder = BitFieldSubCommands.create().
                get(BitFieldSubCommands.BitFieldType.unsigned(6));
        final BitFieldSubCommands bitFieldSubCommands = fieldGetBuilder.valueAt(0l);
        final List<Long> longs = connection.bitField(bitKey, bitFieldSubCommands);
        log.info("Bit count:{}",longs.get(0));
    }

    @org.junit.jupiter.api.Test
    public void testHyperLogLog(){
        Category category = Category.builder().price(new BigDecimal("34.56")).id(1).build();
        log.info("cate:{}",category);

    }

}
