package leetcode;

public class _7__NumsReverse {
    public int reverse(int x) {
        if (x < 10 && x > -10) return x;
        StringBuffer stringBuffer = new StringBuffer();
        boolean flag = true;
        if (x < 0) {
            stringBuffer.append((x*-1));
            flag = false;
        }else{
            stringBuffer.append(x);
        }
        stringBuffer.reverse();
        int result = 0;
        try {
            result = Integer.parseInt(stringBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!flag) return -1 * result;
        return result;
    }
}
