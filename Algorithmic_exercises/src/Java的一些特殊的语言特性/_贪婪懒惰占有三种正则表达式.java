package Java的一些特殊的语言特性;

public class _贪婪懒惰占有三种正则表达式 {
    public static void main(String[] args) {
        String regex1 = "a{1,4}?ab";    //a进行的是懒惰匹配，
        String regex2 = "a{1,}+ab";   //往多的占有，最后匹配4个a
        String regex3 = "[ab]{1,5}+";       //占有，导致最后的ab没有任何一个a可以匹配
        String regex31 = "[ab]{1,5}+ab";       //★占有，导致最后的ab无法匹配a和b★
        String regex32 = "[ab]{1,4}+ab";       //★占有，导致最后的ab只能匹配a，无法匹配b★
        String regex33 = "[ab]{1,3}+ab";       //★占有，导致最后的ab刚好只能匹配a,b★
        String regex34 = "[ab]{1,2}+ab";       //★占有，导致最后的a匹配到的是a，b匹配到的也还是a；★
        String regex341 = "[ab]{1,2}+ab$";       //占有，导致最后的a匹配到的是a，b匹配到的也还是a；
        String regex35 = "[ab]{0,4}?ab$";      //懒惰，"ab"前部分的[ab]{}部分一旦匹配成功就会尝试"ab"，失败就会延长[ab]{}部分，
                                                //直到末尾的"ab"匹配成功，否则返回false ，此处$可有可无，只是用于提示；
//        String regex34 = "[ab]{1,2333333}?ab";
        String regex4 = "a+?ab";       //懒惰    (匹配尽可能少，剩下的可交给其他正则表达式进行匹配)
        String regex5 = "a+ab";        //贪婪    (匹配尽可能多，剩下的可交给其他正则表达式进行匹配)
        String regex6 = "a?+ab";       //占有 ★★★ (匹配最多(由前一位范围符号可决定的最大范围)，与贪婪唯一不同：不进行缩小，无论是多了还是少了，如果无法匹配直接返回false)
        //+ ： 1个或者多个    {1,}
        //* :  任意          {0,}
        //？： 1或者0 相当于{0,1}

        //在范围正则符号 +、*、？、｛n,m｝后接正则符号： “+”表示占有；“？”表示懒惰；“”不加表示贪婪

        String str0 = "aaaab";


        System.out.println("regex1:" + str0.matches(regex1));
        System.out.println("regex2:" + str0.matches(regex2));
        System.out.println("regex3:" + str0.matches(regex3));
        System.out.println("regex31:" + str0.matches(regex31));
        System.out.println("regex32:" + str0.matches(regex32));
        System.out.println("regex33:" + str0.matches(regex33));
        System.out.println("regex34:" + str0.matches(regex34));
        System.out.println("regex341:" + str0.matches(regex341));
        System.out.println("regex35:" + str0.matches(regex35));
        System.out.println("regex4:" + str0.matches(regex4));
        System.out.println("regex5:" + str0.matches(regex5));
        System.out.println("regex6:" + str0.matches(regex6));
    }
}
