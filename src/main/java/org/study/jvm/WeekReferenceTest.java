package org.study.jvm;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WeekReferenceTest {

    private static Integer _4M = 4*1024*1024;

    public void test() throws IOException {
        List<WeakReference<byte[]>> strings = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            strings.add(new WeakReference<byte[]>(new byte[_4M]));
        }
        strings.forEach(softReference -> {
            System.out.println(softReference.get());
        });
        System.in.read();
    }
}
