package com.memo.o2o.dao;

import com.memo.dao.ProductCategoryDao;
import com.memo.entity.ProductCategory;
import com.memo.o2o.BaseTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testDeleteProductCategory() throws Exception{
        Long shopId = 11L ;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategory(shopId);

        for (ProductCategory pc : productCategoryList){
            if ("商品分类1".equals(pc.getProductCategoryName()) || "商品类别2".equals(pc.getProductCategoryName())){
                int effectedNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(),shopId);
                if (effectedNum > 0 )System.out.println(effectedNum);
            }
        }



    }


    @Test
    public void testProductCategoryInsert(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("商品类别1");
        productCategory.setPriority(4);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(11L);

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("商品类别2");
        productCategory2.setPriority(5);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(11L);

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory2);
        int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
        System.out.println(effectedNum);




    }

    @Test
    public void queryProductCategoryTest(){
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategory(1L);
        System.out.println(productCategoryList.size());
    }
}
