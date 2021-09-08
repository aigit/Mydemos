package org.study.concurrent.singleon;

/**
 * 静态内部类的单例
 */
public class InnerclassSingle {

    public static InnerclassSingle getInstance(){
        return InnerSingle.INNER_SINGLE;
    }
    private static class InnerSingle{
        private static InnerclassSingle INNER_SINGLE=new InnerclassSingle();;
    }

}
