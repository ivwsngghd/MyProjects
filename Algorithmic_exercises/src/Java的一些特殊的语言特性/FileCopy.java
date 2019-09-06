package Java的一些特殊的语言特性;

import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        File file = new File("F:\\Afile.txt");
        File file1 = new File("F:\\target");
        File file2 = new File("F:\\target\\Afile.txt");
        if (!file1.exists()){
            file1.mkdirs();
        }

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(file2);
            byte[] buf = new byte[1024];
            int byteRead;
            //把数据读取进缓存数组 buf
            //最后数据结尾读取的坐标由byteRead记录
            //如果没有数据则会返回-1直接跳出
            while ((byteRead = inputStream.read(buf)) != -1) {
                outputStream.write(buf,0,byteRead);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2.FileChannel 实现文件赋值
        //3. Commons IO组件(第三方包)实现文件复制
        //FileUtils.copyFile(Source,Target);

    }
}
