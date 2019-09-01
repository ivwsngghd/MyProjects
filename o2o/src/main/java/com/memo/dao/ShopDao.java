package com.memo.dao;

import com.memo.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

    /**
     * 分页查询店铺，可输入的条件：店铺名(模糊查询)，店铺状态，店铺类别，区域Id, owner
     * @param shopCondition
     * @param rowIndex 从第几行开始取
     * @param pageSize 一页的数据条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,@Param("rowIndex") int rowIndex,@Param("pageSize")int pageSize);

    /**
     * 返回queryShopList的总数
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
    /**
     * 新增商铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    //更新店铺
    int updateShop(Shop shop);

    //通过shop id 查询店铺
    Shop queryByShopId(Long shopId);

}
