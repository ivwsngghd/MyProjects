package com.memo.exceptions;

//封装
public class ShopOperationException extends RuntimeException{
    public ShopOperationException(String msg){
        super(msg);
    }
}
