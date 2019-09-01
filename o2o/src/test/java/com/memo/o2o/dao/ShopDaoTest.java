package com.memo.o2o.dao;

import com.memo.dao.ShopDao;
import com.memo.entity.Area;
import com.memo.entity.PersonInfo;
import com.memo.entity.Shop;
import com.memo.entity.ShopCategory;
import com.memo.o2o.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testQueryShopList(){
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        List<Shop> shopList = shopDao.queryShopList(shopCondition,0,5);
        System.out.println("该用户的数据条数：" + shopList.size());
        int count = shopDao.queryShopCount(shopCondition);
        System.out.println("该用户店铺总数：" + count);



    }

    @Test
    public void testQueryShop(){
        Shop shop = shopDao.queryByShopId(11l);
        System.out.println("areaName：" + shop.getArea().getAreaName());
        System.out.println("areaId：" + shop.getArea().getAreaId());
    }
    @Test
    public void testShopDao(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();

        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1l);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1,effectedNum);


    }

    @Test
    public void testShopDaoUpdate(){
        Shop shop = new Shop();
        shop.setShopId(1l);
        shop.setShopDesc("测试描述23333333");
        shop.setShopName("测试名字");
        shop.setShopAddr("测试地址");
        shop.setShopImg("测试的图片路径");
        shop.setLastEditTime(new Date());
        int effectNum;
        System.out.println(effectNum = shopDao.updateShop(shop));
        assertEquals(1,effectNum);
    }

}
