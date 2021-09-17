package org.study.jvm;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SoftReferenceTest {

    private static final int _4M = 4*1024*1024;
    private Object Reference;

    public void test() throws IOException, InterruptedException {
        List<SoftReference<byte[]>> strings = new ArrayList<>();
        ReferenceQueue<byte[]> referenceReferenceQueue = new ReferenceQueue<>();
        for (int i = 0; i < 2; i++) {
            SoftReference<byte[]> sf =new SoftReference<byte[]>(new byte[_4M],referenceReferenceQueue);
            strings.add(sf);
            System.out.println(strings.size());
        }

        while ((Reference=referenceReferenceQueue.poll())!=null){
            strings.remove(Reference);
        }
        strings.forEach(softReference -> {
            System.out.println(softReference.get());
        });
        System.in.read();
    }
}
