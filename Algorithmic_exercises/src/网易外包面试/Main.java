package 网易外包面试;

import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int nums[] = new int[]{2,3,2,1,4,3};
        System.out.println(isRepeated(nums));

    }

    public static int isRepeated(int nums[]){
        if (nums.length < 2) return -1;
        Set<Integer> integerSet = new TreeSet<>();  //Set集合，实现了hashcode()和equals()方法，确保元素不重复
        for (int i = 0; i < nums.length; i++) {
            if (integerSet.contains(nums[i])) return i; //重复的出口
            else integerSet.add(nums[i]);
        }
        return -1;  //无重复
    }

    public static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a) {
        if (a == null || a.length == 0 || a.length == 1) return;
        quickSort(a, 0, a.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a, int left, int right) {
        //开始分割：
        if (left > right) return;    //递归出口
        AnyType base = a[left];    //base作为枢纽，而且此时a[left]<=a[center](base)<=a[right]
        int i = left, j = right;
        while (i != j) {
            //先选大
            while (a[j].compareTo(base) >= 0 && i < j) j--; // >= 0 之所以允许相同是为了去重，也因此该算法不稳定
            //后选小
            while (a[i].compareTo(base) <= 0 && i < j) i++; // 最后结果是i一定会和j重合(不会交叉)，而此时这个位置，正是枢纽应该存储的位置；
            if (i < j) swapReferences(a, i, j);
        }

        //此时i和j已经重合(枢纽应该存放的位置)，枢纽更新，而此时i位置的值必定小于等于枢纽值(因为先筛大的原因，先从右开始往左移动的原因，反之则会是必定大于等于枢纽值)
        a[left] = a[i];//这个值必定小于等于枢纽(右移中...)
        System.out.println(a[i].compareTo(base));
        a[i] = base;    // i的这个位置是枢纽，以下对其两边进行余下的快排

        quickSort(a, left, i - 1);
        quickSort(a, i + 1, right);
    }

    final private static <AnyType extends Comparable<? super AnyType>> void swapReferences(AnyType a[], int left, int right) {
        AnyType temp;
        temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }


}
