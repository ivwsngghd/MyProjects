package com.memo.o2o.service;

import com.memo.dto.ImageHolder;
import com.memo.dto.ShopExecution;
import com.memo.entity.Area;
import com.memo.entity.PersonInfo;
import com.memo.entity.Shop;
import com.memo.entity.ShopCategory;
import com.memo.enums.ShopStateEnum;
import com.memo.o2o.BaseTest;
import com.memo.service.AreaService;
import com.memo.service.ShopService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;

    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() throws FileNotFoundException {
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
        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("2333");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("D:/java图片处理/跳票2.jpg");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(),is);
        ShopExecution se = shopService.addShop(shop,imageHolder);
        System.out.println(se);
        System.out.println(se.getShop());
        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
    }

    @Test
    public void testGetAreaList(){
        List<Area> areaList = areaService.getAreaList();
        assertEquals("区域1",areaList.get(0).getAreaName());
    }

}