package org.study.str;

import org.study.util.PinyinUtil;

import java.io.File;
import java.sql.Time;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringTest {

    public void test() throws ExecutionException, InterruptedException {

        /*final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        final ScheduledFuture<?> schedule = scheduledExecutorService.schedule(()->{
            final String pinyin = PinyinUtil.handle("我测一个");
            System.out.println("我开始跑了"+pinyin);
        }, 13, TimeUnit.SECONDS);*/



        /*for (int i = 0; i < 10; i++) {
            System.out.print("a");
        }*/
        String s = new String("a")+new String("b");
        //s.intern();
        System.out.println(s=="ab");

        String url = "https://admin-test-v2.crc-musiconline.com/cloud/audio/20220218/78956129018c43ec99bc7ce5f580e46c.mp3";
        String musicName = url.substring(url.lastIndexOf("/")+1,url.length());
        System.out.println(musicName);
        System.out.println(musicName.split("\\.")[0]+"-"+null);

        String url1 = "/cloud/audio/20220316/aaf62cfbd3364ab1a01ffb94f3b46144-m-0.mp3";
        System.out.println(url1.substring(url1.lastIndexOf("/")+1,url1.length()));


        /*String sbxiede="器乐=西洋乐器乐曲=小号";
        String[] split = sbxiede.split("=");
        System.out.println("shabxiede:"+split[3]);*/

        final List<Integer> collect = Stream.of(1, 2, 4).collect(Collectors.toList());
        collect.forEach(System.out::println);

        String domain = "cn.blog.xyz";
        System.out.println("格式:"+domain.substring(domain.lastIndexOf(".")+1));

    }

}
