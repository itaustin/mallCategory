package com.rbt.diamond.public_bean;

import java.io.Serializable;

public class LoginUserBean implements Serializable {

    /**
     * code : 1
     * msg : 登录成功
     * data : {"user_id":10118,"token":"9ad76c0290cf80abffe2e221b0c5b7c5"}
     */

    private Integer code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * user_id : 10118
         * token : 9ad76c0290cf80abffe2e221b0c5b7c5
         */

        private Integer user_id;
        private String token;

        public Integer getUser_id() {
            return user_id;
        }

        public void setUser_id(Integer user_id) {
            this.user_id = user_id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
