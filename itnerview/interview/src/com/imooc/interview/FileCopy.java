package com.imooc.interview;

import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        //1. 利用JAVA ＩＯ
        File source = new File("e:/file1.txt");
        File target = new File("e:/file2.txt");
        InputStream input = null;
        OutputStream output = null;
        BufferedOutputStream bufOS;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(target);

            bufOS = new BufferedOutputStream(output);

            byte[] buffer = new byte[1024];
            int byteReadCurLocate;
            while ((byteReadCurLocate = input.read(buffer)) != -1) {
//                output.write(buffer , 0 , byteReadCurLocate);         //每读取一次便立刻输出，对硬盘读写操作频繁，效率低
                bufOS.write(buffer, 0, byteReadCurLocate);
            }
            bufOS.flush();      //如果没有了这个语句，则不会有数据输出;读取完成后再进行输出，对硬盘损耗低
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //2. FileChannel实现文件复制
        //3. Commons IO组件实现文件复制
        //FileUtils.copyFile(Source , Target);
        //4. Java 7 提供了Files类
        //Files.copy(Source,Target)
    }
}
