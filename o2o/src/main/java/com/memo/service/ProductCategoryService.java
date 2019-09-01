package com.memo.service;

import com.memo.dto.ProductCategoryExecution;
import com.memo.entity.ProductCategory;
import com.memo.exceptions.ProductCategoryOperationExecption;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductCategoryService {
    /**
     * 查询指定的某个店铺下所有的商品类别
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(Long shopId);


    /**
     * 用于批量添加商品分类
     * @param productCategoryList
     * @return
     * @throws ProductCategoryOperationExecption
     */
    ProductCategoryExecution batchAddProductCategory(List <ProductCategory> productCategoryList) throws ProductCategoryOperationExecption;

    ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId) throws ProductCategoryOperationExecption;
}
