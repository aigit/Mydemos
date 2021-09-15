package org.study.str;

public class StringTest {

    public void test(){
        for (int i = 0; i < 10; i++) {
            System.out.print("a");
        }
        String s = new String("a")+new String("b");
        s.intern();
        System.out.print(s=="ab");
    }

}
