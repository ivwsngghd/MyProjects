package com.memo.service;

import com.memo.dto.ProductCategoryExecution;
import com.memo.entity.ProductCategory;
import com.memo.entity.ShopCategory;
import com.memo.exceptions.ProductCategoryOperationExecption;

import java.util.List;

public interface ShopCategoryService {
    /**
     * 用于获取该商铺名下的所有商品分类
     * @param shopCategoryCondition
     * @return
     */
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}

