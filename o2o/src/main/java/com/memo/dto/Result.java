package com.memo.dto;

import java.io.Serializable;

public class Result<DataType> implements Serializable {
    private DataType data;
    private boolean success;
    private String errorMsg;
    private int errorCode;

    public Result(){

    }

    //Service操作成功时使用的构造器
    public Result(DataType data, boolean success) {
        this.data = data;
        this.success = success;
    }

    //操作失败使用的构造器
    public Result(boolean success, String errorMsg, int errorCode) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }
}
