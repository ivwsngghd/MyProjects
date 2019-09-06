package leetcode;

import java.util.Scanner;

//        int a[] = new int[]{1, 2, 3, 4, 9, 8, 7, 6, 5, 10, 11, 12, 13};
//        int a[] = new int[]{3, 1, 2, 4};
public class _赛码_数组反转 {
    public static void main(String[] args) {
//        int a[] = new int[]{2, 1, 3, 4};
        Scanner in = new Scanner(System.in);
        int a[] = new int[in.nextInt()];
        for (int i = 0; i < a.length; i++) {
            a[i] = in.nextInt();
        }
        if (a.length < 2) return;
        Integer right = null, left = null;
        int i = 0;
        for (; i < a.length - 2; i++) {
            if (a[i] > a[i + 1]) {
                left = i;
                break;
            }
        }
        for (int j = a.length - 1; j > i; j--) {
            if (a[j] < a[j - 1]) {
                right = j;
                break;
            }
        }

        if (canBeSorted(a, left, right)) {
            System.out.println("yes");
            return;
        }
        System.out.println("no");
    }

    private static boolean canBeSorted(int a[], Integer left, Integer right) {
        for (int i = left, j = right; i < j; i++) {
            if (a[i] < a[i + 1]) return false;
        }

        if (left - 1 > 0 && a[left - 1] > a[right]) {
            return false;
        }
        if (right + 1 < a.length - 1 && a[right + 1] < a[left]) {
            return false;
        }

        return true;
    }
}


