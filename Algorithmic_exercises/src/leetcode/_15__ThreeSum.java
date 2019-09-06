package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _15__ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);  //默认从小到大排序

        List<List<Integer>> result = new LinkedList<>();    //用于返回

        if (nums.length == 0 || nums[0] > 0 || nums[nums.length - 1] < 0) return result;    //排除3个极端情况

        for (int carry = 0; carry < nums.length; carry++) {
            if (carry > 0 && nums[carry] == nums[carry - 1]) continue; //去掉重复


            int left = carry + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right] + nums[carry];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[left], nums[carry], nums[right]));

                    //得到了符合条件的情况后，去重***********
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;   //往右移动
                } else if (sum < 0) {
                    left++;     //左移
                }
            }
        }

        return result;
    }
}
