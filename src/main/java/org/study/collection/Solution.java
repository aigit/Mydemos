package org.study.collection;

import java.util.List;

public class Solution {

    public ListNode detectCycle(ListNode head) {
        ListNode current = head;
        if(current.next==null){
            return current;
        }
        ListNode before = head.next.next;
        for(;;){
            if(before==null){
                return null;
            }
            if(current==before){
                return current;
            }
            current = current.next;
            before = before.next.next;

        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(-4);

    }
}


class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
      val = x;
      next = null;
  }

    public void setNext(ListNode next) {
        this.next = next;
    }
}

