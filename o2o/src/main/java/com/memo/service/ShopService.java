package com.memo.service;

import com.memo.dto.ImageHolder;
import com.memo.dto.ShopExecution;
import com.memo.entity.Shop;
import com.memo.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    /**
     * 注册店铺信息，包括图片处理
     * @param shop  要注册的店铺信息
     * @param thumbnail 上传的图片和名字，名字用于获取文件的后缀，记录文件类型
     * @return
     * @throws ShopOperationException
     */
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

    /**
     * 更新店铺信息，包括对图片的处理
     * @param shop
     * @param thumbnail
     * @return
     * @throws ShopOperationException
     */
    ShopExecution modifyShop(Shop shop,ImageHolder thumbnail) throws ShopOperationException;

    /**
     * 通过店铺Id获取店铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(Long shopId);

    /**
     *
     * @param shopCondition
     * @param pageIndex 第几页
     * @param pageSize  一页共几条数据
     * @return
     */
    ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);

}
