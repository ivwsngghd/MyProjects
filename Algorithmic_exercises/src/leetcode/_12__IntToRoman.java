package leetcode;

public class _12__IntToRoman {
    public String intToRoman(int num) {
        int tempt;
        StringBuilder result = new StringBuilder();
        //千位转换
        tempt = (num / 1000);
        for (int i = 0; i < tempt; i++) {
            result.append('M');
        }
        //千位转换完毕

        //百位转换
        tempt = (num / 100) % 10;
        if (tempt >= 5) {
            if (tempt == 9) result.append("CM");    //特殊数字900
            else {
                result.append('D');
                for (int i = 5; i < tempt; i++) {
                    result.append('C');
                }
            }
        } else {
            if (tempt == 4) result.append("CD");
            else {
                for (int i = 0; i < tempt; i++) {
                    result.append('C');
                }
            }
        }
        //百位转换完毕

        //十位转换
        tempt = (num / 10) % 10;
        if (tempt >= 5) {
            if (tempt == 9) result.append("XC");    //特殊数字90
            else {
                result.append('L');
                for (int i = 5; i < tempt; i++) {
                    result.append('X');
                }
            }
        } else {
            if (tempt == 4) result.append("XL");
            else {
                for (int i = 0; i < tempt; i++) {
                    result.append('X');
                }
            }
        }
        //十位转换完毕

        //个位转换
        tempt = num % 10;
        if (tempt >= 5) {
            if (tempt == 9) result.append("IX");    //特殊数字9
            else {
                result.append('V');
                for (int i = 5; i < tempt; i++) {
                    result.append('I');
                }
            }
        } else {
            if (tempt == 4) result.append("IV");
            else {
                for (int i = 0; i < tempt; i++) {
                    result.append('I');
                }
            }
        }
        //个位转换完毕

        return result.toString();
    }
}
