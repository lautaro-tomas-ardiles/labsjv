import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> mapNums = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            mapNums.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (
                mapNums.containsKey(target - nums[i]) &&
                i != mapNums.get(target - nums[i])
            ) {
                return new int[]{i, target - nums[i]};
            }
        }
        return new int[]{};
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {  }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }

        return dummyHead.next;
    }

    public static double findMedianSortedArrays(int[] num1, int[] num2) {
        ArrayList<Integer> sum = new ArrayList<>();
        for (int i : num1) sum.add(i);
        for (int j : num2) sum.add(j);
        sum.sort(Integer::compareTo);
        double result ;

        if (sum.size() == 1) return sum.getFirst();

        int medial = sum.size() / 2;
        if (medial == 0) return 0.0;

        if (sum.size() % 2 == 1) {
            result = sum.get( ( sum.size() - 1 ) / 2);
        }else {
            result = (medial + (medial + 1)) / 2.0;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] A = {1,3};
        int[] B = {2,7};

        System.out.println(findMedianSortedArrays(A,B));
    }
}
