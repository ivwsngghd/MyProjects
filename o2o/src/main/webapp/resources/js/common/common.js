function changeVerifyCode(img){
    img.src="../Kaptcha?" + Math.floor(Math.random() * 100);
}

//用于获取URI地址对应参数的值 : ?name=xxxx
function getQueryString(name) {
    var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r != null){
        return decodeURIComponent(r[2]);
    }
    return "";
}