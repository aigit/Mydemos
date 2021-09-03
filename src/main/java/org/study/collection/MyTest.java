package org.study.collection;

public class MyTest {

    private static int min = -1;
    private static int count = 0;

    /*public static void main(String[] args) {
       // int[] myarr = {9,1,2,3,4,5,6,7,8};
        //int[] myarr = {7,8,9,1,2,3,4,5,6};
        int[] myarr = {56,57,58,59,60,61,62,63,64,65,66,67,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55};
        *//*int[] myarr = new int[68];
        for (int i = 0; i < myarr.length; i++) {
            myarr[i]=i;
        }
        for (int i = 0; i <myarr.length ; i++) {
            System.out.print(myarr[i]+",");
        }*//*
        try {
            findMin(myarr,0,myarr.length-1);
            System.out.println("最小的数是==="+min);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private static void findMin(int[] myarr,int start,int end) throws Exception{
        System.out.println("走了:"+count++);
        if(end-start>0){
            if((end-start)==1){
                if(myarr[end]<myarr[start]){
                    min = myarr[end];
                }
            }else {
                if(myarr[start]>myarr[end]){
                    int m = (start+end)/2;
                    min = myarr[m];
                    if(myarr[start]>myarr[m]){
                        findMin(myarr,start,m);
                    }
                    if(myarr[m]>myarr[end]){
                        findMin(myarr,m,end);
                    }
                }
            }
        }
    }

}
