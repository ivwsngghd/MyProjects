package leetcode;

//盛水最多的容器
public class _11_MaxArea {
    public int maxArea(int[] height) {
        int maxArea = 0;
        /*
        for (int i = 0; i < height.length; i++) { //选定左边桶
            for (int j = height.length-1; j >0 ; j--) {
                int area = (j-i) * (height[j]>height[i]?height[i]:height[j]) ;
                if (area > maxArea) maxArea = area ;
            }
        }
         */
        //优化方案：最优的情况下必定是移动短的那一根，令移动造成空间减少的影响降到最低
        int l = 0;
        int r = height.length - 1;
        while (r > l) {
            int area = (r - l) * Math.min(height[l], height[r]);
            if (height[l] < height[r]) l++;
            else r--;

            if (area > maxArea) maxArea = area;
        }

        return maxArea;
    }
}
