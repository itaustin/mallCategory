package com.rbt.diamond.public_bean;

import java.io.Serializable;
import java.util.List;

public class PointsBean implements Serializable {

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int points_id;
        private int order_id;
        private int type;
        private int user_id;
        private String points;
        private String description;
        private String consignment_money;
        private int is_delete;
        private int wxapp_id;
        private String create_time;
        private OrderBean order;
        private UserBean user;

        public int getPoints_id() {
            return points_id;
        }

        public void setPoints_id(int points_id) {
            this.points_id = points_id;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getConsignment_money() {
            return consignment_money;
        }

        public void setConsignment_money(String consignment_money) {
            this.consignment_money = consignment_money;
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

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class OrderBean {
            private int order_id;
            private String order_no;
            private String total_price;
            private String order_price;
            private int coupon_id;
            private int audit_image_id;
            private String coupon_money;
            private String points_money;
            private int points_num;
            private String pay_price;
            private UpdatePriceBean update_price;
            private String buyer_remark;
            private PayTypeBean pay_type;
            private PayStatusBean pay_status;
            private int pay_time;
            private DeliveryTypeBean delivery_type;
            private int extract_shop_id;
            private int extract_clerk_id;
            private String express_price;
            private int express_id;
            private String express_company;
            private String express_no;
            private DeliveryStatusBean delivery_status;
            private int delivery_time;
            private ReceiptStatusBean receipt_status;
            private int receipt_time;
            private OrderStatusBean order_status;
            private int points_bonus;
            private int is_settled;
            private int is_audit;
            private int is_area_bonus;
            private String transaction_id;
            private int is_comment;
            private int order_source;
            private int order_source_id;
            private int user_id;
            private int is_delete;
            private String create_time;
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

            public int getAudit_image_id() {
                return audit_image_id;
            }

            public void setAudit_image_id(int audit_image_id) {
                this.audit_image_id = audit_image_id;
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

            public PayStatusBean getPay_status() {
                return pay_status;
            }

            public void setPay_status(PayStatusBean pay_status) {
                this.pay_status = pay_status;
            }

            public int getPay_time() {
                return pay_time;
            }

            public void setPay_time(int pay_time) {
                this.pay_time = pay_time;
            }

            public DeliveryTypeBean getDelivery_type() {
                return delivery_type;
            }

            public void setDelivery_type(DeliveryTypeBean delivery_type) {
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

            public DeliveryStatusBean getDelivery_status() {
                return delivery_status;
            }

            public void setDelivery_status(DeliveryStatusBean delivery_status) {
                this.delivery_status = delivery_status;
            }

            public int getDelivery_time() {
                return delivery_time;
            }

            public void setDelivery_time(int delivery_time) {
                this.delivery_time = delivery_time;
            }

            public ReceiptStatusBean getReceipt_status() {
                return receipt_status;
            }

            public void setReceipt_status(ReceiptStatusBean receipt_status) {
                this.receipt_status = receipt_status;
            }

            public int getReceipt_time() {
                return receipt_time;
            }

            public void setReceipt_time(int receipt_time) {
                this.receipt_time = receipt_time;
            }

            public OrderStatusBean getOrder_status() {
                return order_status;
            }

            public void setOrder_status(OrderStatusBean order_status) {
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

            public int getIs_audit() {
                return is_audit;
            }

            public void setIs_audit(int is_audit) {
                this.is_audit = is_audit;
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

            public static class PayStatusBean {
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

            public static class DeliveryTypeBean {
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

            public static class DeliveryStatusBean {
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

            public static class ReceiptStatusBean {
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

            public static class OrderStatusBean {
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
        }

        public static class UserBean {
            private int user_id;
            private String open_id;
            private String username;
            private String password;
            private String nickName;
            private String avatarUrl;
            private String alipay_name;
            private String alipay_account;
            private String alipay_img;
            private String wechat_img;
            private String bank_realname;
            private String bank_name;
            private String bank_account;
            private String gender;
            private String country;
            private String province;
            private String real_name;
            private String id_card;
            private String city;
            private int first_num;
            private int second_num;
            private int third_num;
            private int address_id;
            private String balance;
            private String pay_money;
            private String points;
            private String mall_points;
            private String freeze_points;
            private String total_points;
            private String user_gold;
            private String team_bonus;
            private String expend_money;
            private String margin_money;
            private String commission_money;
            private String total_commission_money;
            private String withdraw_commission_money;
            private String active_code;
            private int level;
            private int is_certification;
            private int is_delete;
            private int grade_id;
            private int wxapp_id;
            private String create_time;
            private String update_time;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getOpen_id() {
                return open_id;
            }

            public void setOpen_id(String open_id) {
                this.open_id = open_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getAlipay_name() {
                return alipay_name;
            }

            public void setAlipay_name(String alipay_name) {
                this.alipay_name = alipay_name;
            }

            public String getAlipay_account() {
                return alipay_account;
            }

            public void setAlipay_account(String alipay_account) {
                this.alipay_account = alipay_account;
            }

            public String getAlipay_img() {
                return alipay_img;
            }

            public void setAlipay_img(String alipay_img) {
                this.alipay_img = alipay_img;
            }

            public String getWechat_img() {
                return wechat_img;
            }

            public void setWechat_img(String wechat_img) {
                this.wechat_img = wechat_img;
            }

            public String getBank_realname() {
                return bank_realname;
            }

            public void setBank_realname(String bank_realname) {
                this.bank_realname = bank_realname;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getBank_account() {
                return bank_account;
            }

            public void setBank_account(String bank_account) {
                this.bank_account = bank_account;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getId_card() {
                return id_card;
            }

            public void setId_card(String id_card) {
                this.id_card = id_card;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getFirst_num() {
                return first_num;
            }

            public void setFirst_num(int first_num) {
                this.first_num = first_num;
            }

            public int getSecond_num() {
                return second_num;
            }

            public void setSecond_num(int second_num) {
                this.second_num = second_num;
            }

            public int getThird_num() {
                return third_num;
            }

            public void setThird_num(int third_num) {
                this.third_num = third_num;
            }

            public int getAddress_id() {
                return address_id;
            }

            public void setAddress_id(int address_id) {
                this.address_id = address_id;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getPay_money() {
                return pay_money;
            }

            public void setPay_money(String pay_money) {
                this.pay_money = pay_money;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getMall_points() {
                return mall_points;
            }

            public void setMall_points(String mall_points) {
                this.mall_points = mall_points;
            }

            public String getFreeze_points() {
                return freeze_points;
            }

            public void setFreeze_points(String freeze_points) {
                this.freeze_points = freeze_points;
            }

            public String getTotal_points() {
                return total_points;
            }

            public void setTotal_points(String total_points) {
                this.total_points = total_points;
            }

            public String getUser_gold() {
                return user_gold;
            }

            public void setUser_gold(String user_gold) {
                this.user_gold = user_gold;
            }

            public String getTeam_bonus() {
                return team_bonus;
            }

            public void setTeam_bonus(String team_bonus) {
                this.team_bonus = team_bonus;
            }

            public String getExpend_money() {
                return expend_money;
            }

            public void setExpend_money(String expend_money) {
                this.expend_money = expend_money;
            }

            public String getMargin_money() {
                return margin_money;
            }

            public void setMargin_money(String margin_money) {
                this.margin_money = margin_money;
            }

            public String getCommission_money() {
                return commission_money;
            }

            public void setCommission_money(String commission_money) {
                this.commission_money = commission_money;
            }

            public String getTotal_commission_money() {
                return total_commission_money;
            }

            public void setTotal_commission_money(String total_commission_money) {
                this.total_commission_money = total_commission_money;
            }

            public String getWithdraw_commission_money() {
                return withdraw_commission_money;
            }

            public void setWithdraw_commission_money(String withdraw_commission_money) {
                this.withdraw_commission_money = withdraw_commission_money;
            }

            public String getActive_code() {
                return active_code;
            }

            public void setActive_code(String active_code) {
                this.active_code = active_code;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getIs_certification() {
                return is_certification;
            }

            public void setIs_certification(int is_certification) {
                this.is_certification = is_certification;
            }

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
            }

            public int getGrade_id() {
                return grade_id;
            }

            public void setGrade_id(int grade_id) {
                this.grade_id = grade_id;
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

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }
        }
    }
}
