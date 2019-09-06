package com.memo.util;

public class PathUtil {
    private static String seperator = "\\\\";//根据对应的运行环境(操作系统)获取对应路径的分隔符

    /**
     * 这里是图片的基路径获取
     * @return
     */
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");  //获取操作系统的名字
        String basePath ;
        if(os.toLowerCase().startsWith("win")){     //win系统的路径
            basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            System.out.println(basePath);
        }else { //Linux系统的路径：
            basePath = "/home/memo/image/";
        }
//        basePath = basePath.replace("/",seperator);
        return basePath;
    }

    /**
     * 获取图片的相对路径
     * @param shopId
     * @return
     */
    public static String getShopImagePath(long shopId){
        String imagePath = "upload/item/shop/" + shopId + "/";
        //此处注意replaceAll方法 会自动跳过"\\"的转义字符；
        return imagePath.replaceAll("/",seperator);
    }
}
