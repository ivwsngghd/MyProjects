package com.memo.web.controller.shopadmin;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.memo.dto.ImageHolder;
import com.memo.dto.ShopExecution;
import com.memo.entity.Area;
import com.memo.entity.PersonInfo;
import com.memo.entity.Shop;
import com.memo.entity.ShopCategory;
import com.memo.enums.ShopStateEnum;
import com.memo.service.AreaService;
import com.memo.service.ShopCategoryService;
import com.memo.service.ShopService;
import com.memo.util.CodeUtil;
import com.memo.util.HttpServletRequestUtil;
import com.memo.util.ImageUtil;
import com.memo.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    /**
     * 从一个店铺里点击进来获取的可修改信息
     *
     * @param request
     * @return
     */
    // TODO
    @RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId <= 0) {
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if (currentShopObj == null) {
                modelMap.put("redirect", true);
                modelMap.put("url", "/o2o/shopadmin/shoplist");
            } else {
                Shop currentShop = (Shop) currentShopObj;
                modelMap.put("redirect", false);
//                modelMap.put("shopId", currentShop.getShopId());
            }
        } else {
            Shop currentShop = shopService.getByShopId(shopId);
            request.getSession().setAttribute("currentShop", currentShop);
            modelMap.put("redirect", false);
        }
        return modelMap;

    }

    @RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopList(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        //session TODO
        HttpSession session = request.getSession();
        PersonInfo user = new PersonInfo();
        user.setUserId(1L); //******默认
        user.setName("当前使用用户名字：测试测试233");
        session.setAttribute("user", user);
        user = (PersonInfo) session.getAttribute("user");
        try {
            Shop shopCondition = new Shop();
            shopCondition.setOwner(user);
            ShopExecution shopExecution = shopService.getShopList(shopCondition, 0, 100);
            modelMap.put("user", user);
            modelMap.put("shopList", shopExecution.getShopList());
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }
    @RequestMapping(value = "/getshopcategorylist",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopCategoryList(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        HttpSession session = request.getSession();
        Shop currentShop = (Shop) session.getAttribute("currentShop");
        if (currentShop == null || currentShop.getShopId()==null){
            modelMap.put("redirect", true);
            modelMap.put("url", "/o2o/shopadmin/shoplist");
        }else {
            try{
                List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(currentShop.getShopCategory());
                modelMap.put("success",true);
                modelMap.put("shopCategoryList",shopCategoryList);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        }
        return modelMap;
    }


    @RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopById(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
//        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        Long shopId = ((Shop)request.getSession().getAttribute("currentShop")).getShopId();
        if (shopId > -1) {
            try {
                Shop shop = shopService.getByShopId(shopId);
                List<Area> areaList = areaService.getAreaList();
                modelMap.put("shop", shop);
                modelMap.put("areaList", areaList);
                modelMap.put("success", true);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());//全部对象
            List<Area> areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    /**
     * 此处用于对商铺进行注册
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        HttpSession session = request.getSession();

        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }

        //1.接收并转化相应的参数，包括店铺信息以及图片信息；
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");   //获取前端发送的JSON字符串
        ObjectMapper mapper = new ObjectMapper();

        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        //此处开始上传图片文件
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext()); //获取会话上下文的相关文件上传内容

        //前端信息递交的时候协商的multipart提交表单类型
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }
        //2.开始注册店铺
        if (shop != null && shopImg != null) {
            //session TODO
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");

            shop.setOwner(owner);

            try {
                ShopExecution se = shopService.addShop(shop, new ImageHolder (shopImg.getOriginalFilename(),shopImg.getInputStream()));
                if (se.getState() == ShopStateEnum.CHECK.getState()) {   //注册成功
                    modelMap.put("success", true);

                    //这是该用户可以操作的商铺列表
                    List<Shop> shopList = (List<Shop>) session.getAttribute("shopList");
                    //判断是否第一次创建商铺
                    if (shopList == null || shopList.size() == 0) {
                        shopList = new ArrayList<>();
                    }
                    shopList.add(se.getShop());
                    session.setAttribute("shopList", shopList);

                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }

            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
        //3.返回结果
    }

    @RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        HttpSession session = request.getSession();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }

        //1.接收并转化相应的参数，包括店铺信息以及图片信息；
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");   //获取前端发送的JSON字符串
        ObjectMapper mapper = new ObjectMapper();

        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
            shop.setShopId(((Shop) session.getAttribute("currentShop")).getShopId());
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        //此处开始上传图片文件
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext()); //获取会话上下文的相关文件上传内容

        //前端信息递交的时候协商的multipart提交表单类型
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");    //获取前端递交的商店缩略图
        }

        //2.修改店铺信息
        if (shop != null && shop.getShopId() != null) {
            ShopExecution se = null;
            try {
                if (shopImg == null) {
                    se = shopService.modifyShop(shop,new ImageHolder (null,null));
                } else {
                    se = shopService.modifyShop(shop, new ImageHolder (shopImg.getOriginalFilename(),shopImg.getInputStream()));
                }

                if (se.getState() == ShopStateEnum.SUCCESS.getState()) {   //操作成功
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺Id");
        }
        //3.返回结果
            return modelMap;
    }

    @RequestMapping(value = "/getcurrentshopId")
    @ResponseBody
    private Map<String,Object> getCurrentShopId(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        System.out.println(((Shop)request.getSession().getAttribute("currentShop")).getShopId());
        modelMap.put("currentShopId",((Shop)request.getSession().getAttribute("currentShop")).getShopId());
        modelMap.put("success",true);
        return modelMap;
    }

}
