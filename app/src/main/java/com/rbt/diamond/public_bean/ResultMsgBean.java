package com.rbt.diamond.public_bean;

import java.io.Serializable;

public class ResultMsgBean implements Serializable {

    /**
     * code : 1
     * msg : 保存成功
     * data :
     */

    private Integer code;
    private String msg;
    private String data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
