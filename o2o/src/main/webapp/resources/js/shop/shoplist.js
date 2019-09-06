$(function () {
    getlist();
    function getlist(e) {
        $.ajax({
            url: "/o2o/shopadmin/getshoplist",
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    handleList(data.shopList);
                    handleUser(data.user);
                }
            }
        });
    }
})

//显示个人信息
function handleUser(data) {
    $('#user-name').text(data.name);
}

//显示个人拥有商铺列表
function handleList(data) {
    var html = '';
    data.map(function (item, index) {
        html += '<div class="row row-shop">'
            + '<div class = "col-40">' + item.shopName + '</div>'
            + '<div class="col-40">' + shopStatus(item.enableStatus) + '</div>'
            + '<div class="col-20">' + goShop(item.enableStatus,item.shopId) + '</div>'
            + '</div>'
    });
    $('.shop-wrap').html(html);
}

//显示店铺审核信息
function shopStatus(status) {
    if (status == 0) {
        return '审核中';
    } else if (status == -1) {
        return '店铺非法';
    } else if (status == 1) {
        return '审核通过';
    }
}

//用于打开想要操作的商店  (1：审核通过，可以进入操作修改)
function goShop(status, id) {
    if (status == 1) {
        return '<a href="/o2o/shopadmin/shopmanagement?shopId=' + id + ' ">进入</a>';
    }else{
        return'';
    }
}

