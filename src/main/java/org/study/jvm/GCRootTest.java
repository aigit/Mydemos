package org.study.jvm;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GCRootTest {

    public static void main(String[] args) {
        List<Object> list = null;
        Date birth = new Date();

        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf(i));
            try {
                TimeUnit.MILLISECONDS.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        new Scanner(System.in).nextLine();
    }

}
