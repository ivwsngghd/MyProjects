package leetcode;

import java.util.Arrays;

public class _16__ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); //排序
        int leastSub = Integer.MAX_VALUE;  //最大的差值
        int result = 0;
        for (int carry = 0; carry < nums.length - 1; carry++) {   //遍历整个数组
            int left = carry + 1, right = nums.length - 1;     //从第二个位置 和最后一个位置开始双指针遍历
            for (; left < right; ) {  //左指针不和右指针重合
                int sum = nums[carry] + nums[right] + nums[left];  //和
                int curMinSub = Math.abs(sum - target); //最近一次计算的绝对值

                if (curMinSub == 0) return target; //距离为0 直接返回
                if (curMinSub < leastSub) {    //如果比最小的绝对值还小，交换记录并且记录
                    leastSub = curMinSub;   //更新最小绝对值
                    result = sum;           //储存最近的最小距离的和
                }
                if (sum < target) { //如果和小于目标值，即偏左，因此右移
                    left++;
                } else if (sum > target) {  //同上理
                    right--;
                }
            }
        }
        return result;
    }
}
