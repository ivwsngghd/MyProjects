package com.memo.o2o.service;

import com.memo.dto.ImageHolder;
import com.memo.dto.ShopExecution;
import com.memo.entity.Area;
import com.memo.entity.PersonInfo;
import com.memo.entity.Shop;
import com.memo.entity.ShopCategory;
import com.memo.enums.ShopStateEnum;
import com.memo.exceptions.ShopOperationException;
import com.memo.o2o.BaseTest;
import com.memo.service.ShopService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;


public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testGetShopList(){
        Shop shopCondition = new Shop();
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(1L);
        shopCondition.setShopCategory(sc);
        ShopExecution shopExecution = shopService.getShopList(shopCondition,1,2);
        System.out.println("店铺列表数为：" + shopExecution.getShopList().size());
        System.out.println("店铺总数为：" + shopExecution.getCount());
    }

    @Test
    public void testModifyShop() throws ShopOperationException, FileNotFoundException{
        Shop shop = new Shop();
        shop.setShopId(20L);
        shop.setShopName("修改后的店铺名称是233333");

        File shopImg = new File("D:\\java图片处理\\watermark.png");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shop.getShopName(),is);

        ShopExecution shopExecution = shopService.modifyShop(shop,imageHolder);
        System.out.println(shopExecution.getShop().getShopName());
        System.out.println("新的图片地址为：" + shopExecution.getShop().getShopImg());
    }

    @Test
    public void testAddShop() throws ShopOperationException,FileNotFoundException{
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);

        shop.setOwner(owner);
        shop.setShopCategory(shopCategory);
        shop.setArea(area);
        shop.setShopName("测试店铺2333");
        shop.setShopDesc("test2333");
        shop.setShopAddr("test地址2333");
        shop.setPhone("13143342320");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("提醒：新店铺，目前正在审核中；");
        File shopImg = new File("D:\\java图片处理\\LiYuanFang.jpg");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shop.getShopName(),is);
        ShopExecution se = shopService.addShop(shop,imageHolder);
        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());


    }

}
