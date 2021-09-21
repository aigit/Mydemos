package org.study.jvm;

import java.util.HashMap;
import java.util.Map;

public class ClassPrepareTest {

    private static Integer a = 10;

    private static Map map = new HashMap();

    public ClassPrepareTest(){
        a=5;
    }



    public static void main(String[] args) {
        System.out.println(ClassPrepareTest.a);
    }

    static {
        a=30;
    }
    private static int b = 5;

}
