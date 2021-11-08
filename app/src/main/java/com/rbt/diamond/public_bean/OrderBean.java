package com.rbt.diamond.public_bean;


import java.util.List;

public class OrderBean {

    private int code;
    private String msg;
    private OrderListBean orderList;

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

    public OrderListBean getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderListBean orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private int order_id;
            private String order_no;
            private String total_price;
            private String order_price;
            private int coupon_id;
            private String coupon_money;
            private String points_money;
            private int points_num;
            private int audit_image_id;
            private int is_audit;
            private String pay_price;
            private UpdatePriceBean update_price;
            private String buyer_remark;
            private PayTypeBean pay_type;
            private PayTypeBean pay_status;
            private int pay_time;
            private PayTypeBean delivery_type;
            private int extract_shop_id;
            private int extract_clerk_id;
            private String express_price;
            private int express_id;
            private String express_company;
            private String express_no;
            private PayTypeBean delivery_status;
            private int delivery_time;
            private PayTypeBean receipt_status;
            private int receipt_time;
            private PayTypeBean order_status;
            private int points_bonus;
            private int is_settled;
            private int is_area_bonus;
            private String transaction_id;
            private int is_comment;
            private int order_source;
            private int order_source_id;
            private int user_id;
            private int is_delete;
            private String create_time;
            private List<GoodsBean> goods;
            private String state_text;

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public String getOrder_price() {
                return order_price;
            }

            public void setOrder_price(String order_price) {
                this.order_price = order_price;
            }

            public int getCoupon_id() {
                return coupon_id;
            }

            public void setCoupon_id(int coupon_id) {
                this.coupon_id = coupon_id;
            }

            public String getCoupon_money() {
                return coupon_money;
            }

            public void setCoupon_money(String coupon_money) {
                this.coupon_money = coupon_money;
            }

            public String getPoints_money() {
                return points_money;
            }

            public void setPoints_money(String points_money) {
                this.points_money = points_money;
            }

            public int getPoints_num() {
                return points_num;
            }

            public void setPoints_num(int points_num) {
                this.points_num = points_num;
            }

            public int getAudit_image_id() {
                return audit_image_id;
            }

            public void setAudit_image_id(int audit_image_id) {
                this.audit_image_id = audit_image_id;
            }

            public int getIs_audit() {
                return is_audit;
            }

            public void setIs_audit(int is_audit) {
                this.is_audit = is_audit;
            }

            public String getPay_price() {
                return pay_price;
            }

            public void setPay_price(String pay_price) {
                this.pay_price = pay_price;
            }

            public UpdatePriceBean getUpdate_price() {
                return update_price;
            }

            public void setUpdate_price(UpdatePriceBean update_price) {
                this.update_price = update_price;
            }

            public String getBuyer_remark() {
                return buyer_remark;
            }

            public void setBuyer_remark(String buyer_remark) {
                this.buyer_remark = buyer_remark;
            }

            public PayTypeBean getPay_type() {
                return pay_type;
            }

            public void setPay_type(PayTypeBean pay_type) {
                this.pay_type = pay_type;
            }

            public PayTypeBean getPay_status() {
                return pay_status;
            }

            public void setPay_status(PayTypeBean pay_status) {
                this.pay_status = pay_status;
            }

            public int getPay_time() {
                return pay_time;
            }

            public void setPay_time(int pay_time) {
                this.pay_time = pay_time;
            }

            public PayTypeBean getDelivery_type() {
                return delivery_type;
            }

            public void setDelivery_type(PayTypeBean delivery_type) {
                this.delivery_type = delivery_type;
            }

            public int getExtract_shop_id() {
                return extract_shop_id;
            }

            public void setExtract_shop_id(int extract_shop_id) {
                this.extract_shop_id = extract_shop_id;
            }

            public int getExtract_clerk_id() {
                return extract_clerk_id;
            }

            public void setExtract_clerk_id(int extract_clerk_id) {
                this.extract_clerk_id = extract_clerk_id;
            }

            public String getExpress_price() {
                return express_price;
            }

            public void setExpress_price(String express_price) {
                this.express_price = express_price;
            }

            public int getExpress_id() {
                return express_id;
            }

            public void setExpress_id(int express_id) {
                this.express_id = express_id;
            }

            public String getExpress_company() {
                return express_company;
            }

            public void setExpress_company(String express_company) {
                this.express_company = express_company;
            }

            public String getExpress_no() {
                return express_no;
            }

            public void setExpress_no(String express_no) {
                this.express_no = express_no;
            }

            public PayTypeBean getDelivery_status() {
                return delivery_status;
            }

            public void setDelivery_status(PayTypeBean delivery_status) {
                this.delivery_status = delivery_status;
            }

            public int getDelivery_time() {
                return delivery_time;
            }

            public void setDelivery_time(int delivery_time) {
                this.delivery_time = delivery_time;
            }

            public PayTypeBean getReceipt_status() {
                return receipt_status;
            }

            public void setReceipt_status(PayTypeBean receipt_status) {
                this.receipt_status = receipt_status;
            }

            public int getReceipt_time() {
                return receipt_time;
            }

            public void setReceipt_time(int receipt_time) {
                this.receipt_time = receipt_time;
            }

            public PayTypeBean getOrder_status() {
                return order_status;
            }

            public void setOrder_status(PayTypeBean order_status) {
                this.order_status = order_status;
            }

            public int getPoints_bonus() {
                return points_bonus;
            }

            public void setPoints_bonus(int points_bonus) {
                this.points_bonus = points_bonus;
            }

            public int getIs_settled() {
                return is_settled;
            }

            public void setIs_settled(int is_settled) {
                this.is_settled = is_settled;
            }

            public int getIs_area_bonus() {
                return is_area_bonus;
            }

            public void setIs_area_bonus(int is_area_bonus) {
                this.is_area_bonus = is_area_bonus;
            }

            public String getTransaction_id() {
                return transaction_id;
            }

            public void setTransaction_id(String transaction_id) {
                this.transaction_id = transaction_id;
            }

            public int getIs_comment() {
                return is_comment;
            }

            public void setIs_comment(int is_comment) {
                this.is_comment = is_comment;
            }

            public int getOrder_source() {
                return order_source;
            }

            public void setOrder_source(int order_source) {
                this.order_source = order_source;
            }

            public int getOrder_source_id() {
                return order_source_id;
            }

            public void setOrder_source_id(int order_source_id) {
                this.order_source_id = order_source_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public String getState_text() {
                return state_text;
            }

            public void setState_text(String state_text) {
                this.state_text = state_text;
            }

            public static class UpdatePriceBean {
                private String symbol;
                private String value;

                public String getSymbol() {
                    return symbol;
                }

                public void setSymbol(String symbol) {
                    this.symbol = symbol;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class PayTypeBean {
                private String text;
                private int value;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }

            public static class GoodsBean {
                private int order_goods_id;
                private int goods_id;
                private String goods_name;
                private int image_id;
                private int deduct_stock_type;
                private int spec_type;
                private String spec_sku_id;
                private int goods_sku_id;
                private String goods_attr;
                private String goods_no;
                private String goods_price;
                private String line_price;
                private String goods_weight;
                private int is_user_grade;
                private int grade_ratio;
                private String grade_goods_price;
                private String grade_total_money;
                private String coupon_money;
                private String points_money;
                private int points_num;
                private int points_bonus;
                private int total_num;
                private String total_price;
                private String total_pay_price;
                private int is_ind_dealer;
                private int dealer_money_type;
                private String first_money;
                private String second_money;
                private String third_money;
                private int is_comment;
                private int order_id;
                private int user_id;
                private int goods_source_id;
                private ImageBean image;

                public int getOrder_goods_id() {
                    return order_goods_id;
                }

                public void setOrder_goods_id(int order_goods_id) {
                    this.order_goods_id = order_goods_id;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public int getImage_id() {
                    return image_id;
                }

                public void setImage_id(int image_id) {
                    this.image_id = image_id;
                }

                public int getDeduct_stock_type() {
                    return deduct_stock_type;
                }

                public void setDeduct_stock_type(int deduct_stock_type) {
                    this.deduct_stock_type = deduct_stock_type;
                }

                public int getSpec_type() {
                    return spec_type;
                }

                public void setSpec_type(int spec_type) {
                    this.spec_type = spec_type;
                }

                public String getSpec_sku_id() {
                    return spec_sku_id;
                }

                public void setSpec_sku_id(String spec_sku_id) {
                    this.spec_sku_id = spec_sku_id;
                }

                public int getGoods_sku_id() {
                    return goods_sku_id;
                }

                public void setGoods_sku_id(int goods_sku_id) {
                    this.goods_sku_id = goods_sku_id;
                }

                public String getGoods_attr() {
                    return goods_attr;
                }

                public void setGoods_attr(String goods_attr) {
                    this.goods_attr = goods_attr;
                }

                public String getGoods_no() {
                    return goods_no;
                }

                public void setGoods_no(String goods_no) {
                    this.goods_no = goods_no;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public String getLine_price() {
                    return line_price;
                }

                public void setLine_price(String line_price) {
                    this.line_price = line_price;
                }

                public String getGoods_weight() {
                    return goods_weight;
                }

                public void setGoods_weight(String goods_weight) {
                    this.goods_weight = goods_weight;
                }

                public int getIs_user_grade() {
                    return is_user_grade;
                }

                public void setIs_user_grade(int is_user_grade) {
                    this.is_user_grade = is_user_grade;
                }

                public int getGrade_ratio() {
                    return grade_ratio;
                }

                public void setGrade_ratio(int grade_ratio) {
                    this.grade_ratio = grade_ratio;
                }

                public String getGrade_goods_price() {
                    return grade_goods_price;
                }

                public void setGrade_goods_price(String grade_goods_price) {
                    this.grade_goods_price = grade_goods_price;
                }

                public String getGrade_total_money() {
                    return grade_total_money;
                }

                public void setGrade_total_money(String grade_total_money) {
                    this.grade_total_money = grade_total_money;
                }

                public String getCoupon_money() {
                    return coupon_money;
                }

                public void setCoupon_money(String coupon_money) {
                    this.coupon_money = coupon_money;
                }

                public String getPoints_money() {
                    return points_money;
                }

                public void setPoints_money(String points_money) {
                    this.points_money = points_money;
                }

                public int getPoints_num() {
                    return points_num;
                }

                public void setPoints_num(int points_num) {
                    this.points_num = points_num;
                }

                public int getPoints_bonus() {
                    return points_bonus;
                }

                public void setPoints_bonus(int points_bonus) {
                    this.points_bonus = points_bonus;
                }

                public int getTotal_num() {
                    return total_num;
                }

                public void setTotal_num(int total_num) {
                    this.total_num = total_num;
                }

                public String getTotal_price() {
                    return total_price;
                }

                public void setTotal_price(String total_price) {
                    this.total_price = total_price;
                }

                public String getTotal_pay_price() {
                    return total_pay_price;
                }

                public void setTotal_pay_price(String total_pay_price) {
                    this.total_pay_price = total_pay_price;
                }

                public int getIs_ind_dealer() {
                    return is_ind_dealer;
                }

                public void setIs_ind_dealer(int is_ind_dealer) {
                    this.is_ind_dealer = is_ind_dealer;
                }

                public int getDealer_money_type() {
                    return dealer_money_type;
                }

                public void setDealer_money_type(int dealer_money_type) {
                    this.dealer_money_type = dealer_money_type;
                }

                public String getFirst_money() {
                    return first_money;
                }

                public void setFirst_money(String first_money) {
                    this.first_money = first_money;
                }

                public String getSecond_money() {
                    return second_money;
                }

                public void setSecond_money(String second_money) {
                    this.second_money = second_money;
                }

                public String getThird_money() {
                    return third_money;
                }

                public void setThird_money(String third_money) {
                    this.third_money = third_money;
                }

                public int getIs_comment() {
                    return is_comment;
                }

                public void setIs_comment(int is_comment) {
                    this.is_comment = is_comment;
                }

                public int getOrder_id() {
                    return order_id;
                }

                public void setOrder_id(int order_id) {
                    this.order_id = order_id;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public int getGoods_source_id() {
                    return goods_source_id;
                }

                public void setGoods_source_id(int goods_source_id) {
                    this.goods_source_id = goods_source_id;
                }

                public ImageBean getImage() {
                    return image;
                }

                public void setImage(ImageBean image) {
                    this.image = image;
                }

                public static class ImageBean {
                    private int file_id;
                    private String storage;
                    private int group_id;
                    private String file_url;
                    private String file_name;
                    private int file_size;
                    private String file_type;
                    private String extension;
                    private int is_user;
                    private int is_recycle;
                    private int is_delete;
                    private int wxapp_id;
                    private String create_time;
                    private String file_path;

                    public int getFile_id() {
                        return file_id;
                    }

                    public void setFile_id(int file_id) {
                        this.file_id = file_id;
                    }

                    public String getStorage() {
                        return storage;
                    }

                    public void setStorage(String storage) {
                        this.storage = storage;
                    }

                    public int getGroup_id() {
                        return group_id;
                    }

                    public void setGroup_id(int group_id) {
                        this.group_id = group_id;
                    }

                    public String getFile_url() {
                        return file_url;
                    }

                    public void setFile_url(String file_url) {
                        this.file_url = file_url;
                    }

                    public String getFile_name() {
                        return file_name;
                    }

                    public void setFile_name(String file_name) {
                        this.file_name = file_name;
                    }

                    public int getFile_size() {
                        return file_size;
                    }

                    public void setFile_size(int file_size) {
                        this.file_size = file_size;
                    }

                    public String getFile_type() {
                        return file_type;
                    }

                    public void setFile_type(String file_type) {
                        this.file_type = file_type;
                    }

                    public String getExtension() {
                        return extension;
                    }

                    public void setExtension(String extension) {
                        this.extension = extension;
                    }

                    public int getIs_user() {
                        return is_user;
                    }

                    public void setIs_user(int is_user) {
                        this.is_user = is_user;
                    }

                    public int getIs_recycle() {
                        return is_recycle;
                    }

                    public void setIs_recycle(int is_recycle) {
                        this.is_recycle = is_recycle;
                    }

                    public int getIs_delete() {
                        return is_delete;
                    }

                    public void setIs_delete(int is_delete) {
                        this.is_delete = is_delete;
                    }

                    public int getWxapp_id() {
                        return wxapp_id;
                    }

                    public void setWxapp_id(int wxapp_id) {
                        this.wxapp_id = wxapp_id;
                    }

                    public String getCreate_time() {
                        return create_time;
                    }

                    public void setCreate_time(String create_time) {
                        this.create_time = create_time;
                    }

                    public String getFile_path() {
                        return file_path;
                    }

                    public void setFile_path(String file_path) {
                        this.file_path = file_path;
                    }
                }
            }
        }
    }
}
