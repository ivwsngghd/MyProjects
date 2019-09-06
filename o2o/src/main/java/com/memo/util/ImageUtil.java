package com.memo.util;

import com.memo.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
//    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static String basePath = PathUtil.getImgBasePath();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);    //记录图片操作记录
    private static String waterMarkPicPath = basePath + "upload/watermark/watermark.png";

    /**
     * 将CommonsMultipartFile转换成File类
     *
     * @param cFile
     * @return
     */
    public static void transferCommonsMultipartFileToFile(CommonsMultipartFile cFile, File file){
        InputStream ins = null;
        OutputStream os = null;
        try {
            ins = cFile.getInputStream();
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = ins.read(buffer))!=-1){
                os.write(buffer,0,bytesRead);
            }

        } catch (Exception e) {
            throw new RuntimeException("调用transferCommonsMultipartFileToFile产生异常：" + e.getMessage());
        }finally {
            try {
                if (os!= null) os.close();
                if (ins != null) ins.close();

            } catch (IOException e) {
                throw new RuntimeException("transferCommonsMultipartFileToFile关闭io时产生异常：" + e.getLocalizedMessage());
            }
        }

    }

    //CommonsMultipartFile
    //保证在各种系统里面都可以生成对应的路径
    public static String generateThumbnail(InputStream thumbnailInputStream, String fileName,String targetAddr) {
        String realFileName = getRandomFileName();      //生成不重复的名字
        String extension = getFileExtension(fileName);          //记录后缀
        makeDirPath(targetAddr);                         //相对路径
        String relativeAddr = targetAddr + realFileName + extension;   //相对存储路径
        logger.debug("current relativeAddr is :" + relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr); //输出路径
        logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnailInputStream).size(2000, 2000) //of() 接收CommonsMultipartFile.getInputStream(),或者File
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(waterMarkPicPath)), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return relativeAddr;
    }

    /**
     * 创建目标路径所涉及到的目录，即/home/work/memo/xxx.jpg
     * home work memo 这三个文件夹都要自动创建
     *
     * @param tagetAddr
     */
    private static void makeDirPath(String tagetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + tagetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }

        dirPath = new File(waterMarkPicPath);
        if (!dirPath.exists()){
            dirPath.mkdirs();
        }
    }



    //生成随机文件名，也可以使用UUID来生成
    public static String getRandomFileName() {
        //获取随机的五位数
        int rannum = r.nextInt(23333) + 10000;
        String nowTimeStr = dateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * storePath 是文件的路径还是目录的路径
     * 如果storePath是文件路径则删除文件
     * 如果是目录路径则删除该目录下的所有文件
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath){
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()){
            if (fileOrPath.isDirectory()){  //删除文件
                File files[] = fileOrPath.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();    //删除路径
        }
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".")); //获取扩展名
    }

    /**
     * 处理详情图，并返回新生成图片的相对值路径
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
        // 获取不重复的随机名
        String realFileName = getRandomFileName();
        // 获取文件的扩展名如png,jpg等
        String extension = getFileExtension(thumbnail.getImageName());
        // 如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        // 获取文件存储的相对路径(带文件名)
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is :" + relativeAddr);
        // 获取文件要保存到的目标路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
        // 调用Thumbnails生成带有水印的图片
        System.out.println("基路径： " + basePath);
        try {
            Thumbnails.of(thumbnail.getImage()).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(waterMarkPicPath)), 0.25f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            System.out.println("BUG原因：" + e.getMessage());
            logger.error(e.toString());
            throw new RuntimeException("创建缩图片失败：" + e.toString());
        }
        // 返回图片相对路径地址
        return relativeAddr;
    }


}
