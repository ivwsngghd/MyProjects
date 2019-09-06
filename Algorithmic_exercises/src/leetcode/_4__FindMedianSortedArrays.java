package leetcode;

public class _4__FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int maxLength = nums2.length + nums1.length;
        double doubles[] = new double[maxLength];
        int flag1 = 0;
        int flag2 = 0;
        for (int i = 0; i < maxLength; i++) {
            if (flag2>=nums2.length || (flag1<nums1.length && nums1[flag1] < nums2[flag2]) ) {
                doubles[i] = nums1[flag1];
                flag1++;
            } else if (flag2 < nums2.length) {
                doubles[i] = nums2[flag2];
                flag2++;
            }
        }
        if (maxLength % 2 == 0) {
            return (doubles[maxLength / 2 - 1] + doubles[maxLength / 2]) / 2;
        }
        return doubles[maxLength / 2];
    }
}
