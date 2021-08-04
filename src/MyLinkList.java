import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyLinkList {

    private static List mylist = new ArrayList();

    public static void main(String[] args) {
        LinkedList mylinkedList = new LinkedList();
        for (int i = 1; i < 14; i++) {
            mylinkedList.add(i);
            System.out.print(i+",");
        }
        System.out.println("******************************");
        resort(mylinkedList,0);
        System.out.println("******************************");
        mylist.forEach(e->{
            System.out.print(e+",");
        });
    }

    /**
     * 排序逃逸
     */
    public static void resort(LinkedList<Integer> myarr, int step){
        if(myarr.size()<=1){
            return;
        }
        step+=2;
        if(step>=myarr.size()){
            step=(step-myarr.size())%2;
        }

        Integer curr = myarr.remove(step);
        mylist.add(curr);
        myarr.forEach(e->{
            System.out.print(e+",");
        });
        System.out.println();
        resort(myarr,step);
    }

}
