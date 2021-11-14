package org.study.collection;

import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args) {
        int[] tmp = {1,2,3,4,5};

        int[] my = Arrays.copyOfRange(tmp,2,5);
        System.out.print("是否相等:"+tmp+"==="+my);
        for (int i = 0; i < my.length; i++) {
            System.out.println(my[i]);
        }
    }
}
