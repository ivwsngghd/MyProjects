package leetcode;

public class _26__RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) return nums.length;
        int left = 1;
        int pos = 1;
        for (; pos < nums.length; pos++) {
            if (nums[pos] > nums[left-1]){
                nums[left]=nums[pos];
                left++;
            }
        }
        return left;
    }
}
