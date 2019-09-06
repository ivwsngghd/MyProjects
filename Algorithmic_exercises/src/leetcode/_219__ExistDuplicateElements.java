package leetcode;

public class _219__ExistDuplicateElements {
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        for(int x = 0; x < nums.length-1 ; x++){
            for(int i = x+1 ; i<nums.length && (i-x <= k); i++){
                if(nums[x] == nums[i]){
                    return true;
                }
            }
        }
        return false;
    }
}
