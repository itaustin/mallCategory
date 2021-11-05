package com.rbt.diamond.public_bean;

public class AddCartBean {

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
        private int cart_total_num;

        public int getCart_total_num() {
            return cart_total_num;
        }

        public void setCart_total_num(int cart_total_num) {
            this.cart_total_num = cart_total_num;
        }
    }
}
