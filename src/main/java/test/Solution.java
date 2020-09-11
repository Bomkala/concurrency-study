package test;

/**
 * @author beimo
 * @date 2020/9/2
 */
class Solution {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int a = 1;
        int i = 0;
        int up = 0;

        ListNode temp1 = l1;
        ListNode temp2 = l2;
        ListNode res = new ListNode(l1.val + l2.val);
        if (l1.val + l2.val >= 10) {
            up = 1;
        }
        ListNode tempNode = res;

        while ((temp1 = l1.next) != null || (temp2 = l2.next) != null) {

            if(temp2 == l2.next){
                break;
            }

            int num = temp1.val + temp2.val + up;
            if (num >= 10) {
                up = 1;
            } else {
                up = 0;
            }
            ListNode next = new ListNode(num);
            tempNode.next = next;
            tempNode = next;
        }

        System.out.println(res);
        return res;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(5);

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(7);

        addTwoNumbers(l1,l2);

    }
}

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
