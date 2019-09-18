package Java的一些特殊的语言特性;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class _双冒号的用处 {
    public static void main(String[] args) {
        List<String> strs = Arrays.asList("a","b","c","d","e");

        //下面提供4种遍历的方法
        //每个元素传进去进行输出；
        strs.forEach(AClass::printValue);

        System.out.println();
        for (String str : strs){
            AClass.printValue(str);
        }

        System.out.println();
        strs.forEach(str -> {
            AClass.printValue(str);
        });

        System.out.println();
        Consumer<String> methodParam = AClass::printValue;
        strs.forEach(s -> methodParam.accept(s));


    }

    private static class AClass {
        public static void printValue(String str){
            System.out.print(str + " ");
        }
    }

}


