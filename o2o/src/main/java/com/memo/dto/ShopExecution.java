package com.memo.dto;

import com.memo.entity.Shop;
import com.memo.enums.ShopStateEnum;

import java.util.List;

/**
 * 在对商店进行操作的时候会出现的各种情况
 * 对此进行相应的标记和处理
 * 获取操作的结果以及操作的Shop对象
 */
public class ShopExecution {

    //结果状态
    private int state;
    //状态表示
    private String stateInfo;
    //店铺数量
    private int count;
    //操作的shop
    private Shop shop;
    //shop列表(查询店铺列表的时候使用)
    private List<Shop> shopList;


    @Override
    public String toString() {
        return "ShopExecution{" +
                "state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", count=" + count +
                ", shop=" + shop.toString() +
                '}';
    }

    public ShopExecution(){

    }

    //店铺操作失败的构造器
    public ShopExecution(ShopStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //店铺操作成功使用的构造器
    public ShopExecution(ShopStateEnum stateEnum,Shop shop){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }
    //店铺操作成功使用的构造器
    public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
