package com.memo.o2o.dao;

import com.memo.dao.ShopCategoryDao;
import com.memo.entity.ShopCategory;
import com.memo.o2o.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCateQuery(){
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
//        assertEquals(3,shopCategoryList.size());

        ShopCategory testCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(2l);
        testCategory.setParent(parentCategory);
        testCategory.setShopCategoryId(3l);
//        shopCategoryList = shopCategoryDao.queryShopCategory(testCategory);
        shopCategoryList = shopCategoryDao.queryShopCategory(null);
//        assertEquals(1,shopCategoryList.size());
        System.out.println(shopCategoryList.size());
    }

}
