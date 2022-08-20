package com.linkjb.camelcomponent.letcode;

/**
 * @ClassName code2
 * @Description TODO
 * @Author shark
 * @Data 2022/7/20 21:41
 **/
public class code2fail {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
            Integer index = 0;
            Integer count = 0;
            count = count+ l1.val*(new Double(Math.pow(10,index)).intValue())+l2.val*(new Double(Math.pow(10,index)).intValue());
            while (l1.next!=null||l2.next!=null){
                index = index+1;
                if(l1.next!=null){
                    count = count+ l1.next.val*(new Double(Math.pow(10,index)).intValue());
                    l1 = l1.next;
                }
                if(l2.next!=null){
                    count = count+ l2.next.val*(new Double(Math.pow(10,index)).intValue());
                    l2 = l2.next;
                }
            }

        System.out.println(count);
        char[] chars = count.toString().toCharArray();
        ListNode s = new ListNode();
        ListNode temp = s;
        for(int i = chars.length-1;i >= 0;i--){
            temp.val = Integer.parseInt(Character.toString(chars[i]));
            if(i!=0){
                temp.next = new ListNode();
                temp = temp.next;
            }
        }
        //temp.val = Integer.parseInt(Character.toString(chars[chars.length-1]));
        return s;



    }

    public static void main(String[] args){
        ListNode l1 = new ListNode();
        l1.val = 1;
        ListNode l12 = new ListNode();
        l12.val = 9;
        ListNode l13 = new ListNode();
        l13.val = 9;
        l1.next=l12;
        l12.next = l13;

        ListNode l2 = new ListNode();
        l2.val = 9;
//        ListNode l22 = new ListNode();
//        l22.val = 6;
//        ListNode l23 = new ListNode();
//        l23.val = 4;
//        l2.next=l22;
//        l22.next = l23;

        addTwoNumbers(l1,l2);

    }


}

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

