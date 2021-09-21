package org.study.jvm;

public class Test
{
    public  Test1 t = new Test1();
    public static int a = 0;
    public static int b;

    public static void main(String[] arg)
    {
        System.out.println(Test.a);
        System.out.println(Test.b);
    }
}

class Test1
{
    public Test1()
    {
        Test.a++;
        Test.b++;
    }
}


