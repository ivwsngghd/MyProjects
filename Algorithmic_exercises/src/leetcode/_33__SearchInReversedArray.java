package leetcode;

public class _33__SearchInReversedArray {
    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if ((nums[0] > target) ^ (nums[0] > nums[mid]) ^ (target > nums[mid])) lo = mid + 1;
            else hi = mid;
        }
        return lo == hi && nums[lo] == target ? lo : -1;
    }
}