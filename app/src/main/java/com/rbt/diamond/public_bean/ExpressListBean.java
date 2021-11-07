package com.rbt.diamond.public_bean;

import java.util.List;

public class ExpressListBean {

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
        private ExpressBean express;

        public ExpressBean getExpress() {
            return express;
        }

        public void setExpress(ExpressBean express) {
            this.express = express;
        }

        public static class ExpressBean {
            private String express_name;
            private String express_no;
            private List<ListBean> list;

            public String getExpress_name() {
                return express_name;
            }

            public void setExpress_name(String express_name) {
                this.express_name = express_name;
            }

            public String getExpress_no() {
                return express_no;
            }

            public void setExpress_no(String express_no) {
                this.express_no = express_no;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                private String time;
                private String context;
                private String ftime;
                private Object areaCode;
                private Object areaName;
                private String status;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getContext() {
                    return context;
                }

                public void setContext(String context) {
                    this.context = context;
                }

                public String getFtime() {
                    return ftime;
                }

                public void setFtime(String ftime) {
                    this.ftime = ftime;
                }

                public Object getAreaCode() {
                    return areaCode;
                }

                public void setAreaCode(Object areaCode) {
                    this.areaCode = areaCode;
                }

                public Object getAreaName() {
                    return areaName;
                }

                public void setAreaName(Object areaName) {
                    this.areaName = areaName;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }
        }
    }
}
