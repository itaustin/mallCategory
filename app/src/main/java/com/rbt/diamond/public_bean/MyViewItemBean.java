package com.rbt.diamond.public_bean;

import java.io.Serializable;

public class MyViewItemBean implements Serializable {

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String eng_name;
        private String name;

        public String getEng_name() {
            return eng_name;
        }

        public void setEng_name(String eng_name) {
            this.eng_name = eng_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
