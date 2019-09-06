package com.memo.service.impl;

import com.memo.dao.ShopDao;
import com.memo.dto.ImageHolder;
import com.memo.dto.ShopExecution;
import com.memo.entity.Shop;
import com.memo.enums.ShopStateEnum;
import com.memo.exceptions.ShopOperationException;
import com.memo.service.ShopService;
import com.memo.util.ImageUtil;
import com.memo.util.PageCalculator;
import com.memo.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
        InputStream inputStream = thumbnail.getImage();
        String fileName = thumbnail.getImageName();

        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }//还有其他信息的验证和判断；

        try {
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");   //RuntimeException 类型在事务中才会进行回滚
            } else {
                //此处存储图片
                if (inputStream != null) {
                    try {
                        addShopImg(shop, inputStream, fileName);  //把图片插入到Shop中，以及上传准备
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }

                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }

            }
        } catch (Exception e) {
            //添加到日志信息里
            e.printStackTrace();
            throw new ShopOperationException("addShop() error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);  //新添加的商铺处于审核状态
    }

    @Override
    public Shop getByShopId(Long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);    //第几页 多少条
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution shopExecution = new ShopExecution();
        if (shopList != null) {
            shopExecution.setShopList(shopList);
            shopExecution.setCount(count);
        } else {
            shopExecution.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return shopExecution;
    }

    @Override
    public ShopExecution modifyShop(Shop shop,ImageHolder thumbnail) throws ShopOperationException {
        InputStream shopImgInputStream = thumbnail.getImage();
        String fileName = thumbnail.getImageName();
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            //1.判断是否需要处理图片
            try {

                if (shopImgInputStream != null && fileName != null && !"".equals(fileName)) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());

                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, shopImgInputStream, fileName); //商店关联新图片的地址
                }
                //2.更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                throw new ShopOperationException("modifyShop error：" + e.getMessage());
            }
        }
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        //该方法会把相对路径和绝对路径进行结合,上传图片并添加水印
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
        shop.setShopImg(shopImgAddr);
    }
}


