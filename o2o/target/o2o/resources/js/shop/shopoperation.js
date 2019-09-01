/*
    获取后台已有的信息，譬如分类等
    收集填写信息，使用Ajax技术获取后台返回的数据
 */

/**
 * 获取到后台已有信息，填充到下拉列表里
 */
$(function () {
    //用于分辨是否是修改店铺
    // var shopId = getQueryString("shopId");
    var isAdd = getQueryString("isAdd");

    var isEdit = true;
    if (isAdd) isEdit = false;

    var registerShopUrl = '/o2o/shopadmin/registershop';
    var shopInfoUrl = "/o2o/shopadmin/getshopbyid";
    var editShopUrl = "/o2o/shopadmin/modifyshop";
    if (!isEdit) {
        getShopInitInfo();
    } else {
        getShopInfo(shopInfoUrl);
    }

    $('#submit').click(function () {
        var shop = {};

        shop.shopName = $('#shopName').val();
        shop.shopAddr = $('#shop-addr').val();
        shop.phone = $('#shop-phone').val();
        shop.shopDesc = $('#shop-desc').val();
        shop.shopCategory = {
            shopCategoryId: $('#shop-category').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        shop.area = {
            areaId: $('#area').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        var shopImg = $('#shop-img')[0].files[0];
        var formData = new FormData();
        formData.append('shopImg', shopImg);                //存储图片流
        formData.append('shopStr', JSON.stringify(shop));   //存储信息
        var verifyCodeActual = $('#j_captcha').val();
        if (!verifyCodeActual) {
            $.toast('请输入验证码！');
            return;
        }
        formData.append('verifyCodeActual', verifyCodeActual);  //验证码
        $.ajax({
            url: (isEdit ? editShopUrl : registerShopUrl),
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.success) {
                    $.toast('提交成功！');
                } else {
                    console.log(data.errMsg);
                    $.toast('提交失败！' + data.errMsg);
                }
                $('#captcha_img').click();
            }
        });
    })

})

//改方法用于初始化修改页面的初始信息
function getShopInitInfo() {
    var initUrl = '/o2o/shopadmin/getshopinitinfo';
    $.getJSON(initUrl, function (data) {
        if (data.success) {
            var tempHtml = '';
            var tempAreaHtml = '';
            data.shopCategoryList.map(function (item, index) {
                tempHtml += '<option data-id=' + item.shopCategoryId + '>'
                    + item.shopCategoryName + '</option>';
            });
            data.areaList.map(function (item, index) {
                tempAreaHtml += '<option data-id=' + item.areaId + '>'
                    + item.areaName + '</option>'
            });
            $('#shop-category').html(tempHtml);
            $('#area').html(tempAreaHtml);
        }
    });
}

function getShopInfo(shopInfoUrl) {
    $.getJSON(shopInfoUrl, function (data) {
        if (data.success) {
            var shop = data.shop;
            $('#shopName').val(shop.shopName);
            $('#shop-addr').val(shop.shopAddr);
            $('#shop-phone').val(shop.phone);
            $('#shop-desc').val(shop.shopDesc);
            var shopCategory =
                '<option data-id=' + shop.shopCategory.shopCategoryId + ' selected>'
                + shop.shopCategory.shopCategoryName
                + '</option>';
            var tempAreaHtml = '';
            data.areaList.map(function (item, index) {
                tempAreaHtml += '<option data-id=' + item.areaId + '>' + item.areaName + '</option>';
            });
            $('#shop-category').html(shopCategory);
            $('#shop-category').attr('disabled', 'disabled');
            $('#area').html(tempAreaHtml);
            $("#area option[data-id='" + shop.area.areaId + "']").attr('selected', 'selected'); //默认信息
        }
    });
}

