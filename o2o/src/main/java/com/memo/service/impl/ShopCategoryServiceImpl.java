package com.memo.service.impl;

import com.memo.dao.ShopCategoryDao;
import com.memo.dto.ProductCategoryExecution;
import com.memo.entity.ProductCategory;
import com.memo.entity.ShopCategory;
import com.memo.exceptions.ProductCategoryOperationExecption;
import com.memo.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }

}
