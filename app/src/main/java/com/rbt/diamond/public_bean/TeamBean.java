package com.rbt.diamond.public_bean;

import java.io.Serializable;
import java.util.List;

public class TeamBean implements Serializable {

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
        private List<RefereeBean> referee;
        private CountBean count;

        public List<RefereeBean> getReferee() {
            return referee;
        }

        public void setReferee(List<RefereeBean> referee) {
            this.referee = referee;
        }

        public CountBean getCount() {
            return count;
        }

        public void setCount(CountBean count) {
            this.count = count;
        }

        public static class CountBean {
            private int first;
            private int second;
            private int total;

            public int getFirst() {
                return first;
            }

            public void setFirst(int first) {
                this.first = first;
            }

            public int getSecond() {
                return second;
            }

            public void setSecond(int second) {
                this.second = second;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class RefereeBean {
            private int id;
            private int dealer_id;
            private int user_id;
            private int level;
            private int wxapp_id;
            private String create_time;
            private int order_count;
            private int order_total_price;
            private UserBean user;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getDealer_id() {
                return dealer_id;
            }

            public void setDealer_id(int dealer_id) {
                this.dealer_id = dealer_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
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

            public int getOrder_count() {
                return order_count;
            }

            public void setOrder_count(int order_count) {
                this.order_count = order_count;
            }

            public int getOrder_total_price() {
                return order_total_price;
            }

            public void setOrder_total_price(int order_total_price) {
                this.order_total_price = order_total_price;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class UserBean {
                private int user_id;
                private String username;
                private String password;
                private String nickName;
                private String avatarUrl;
                private String alipay_name;
                private String alipay_account;
                private String alipay_img;
                private String wechat_img;
                private Object bank_realname;
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
                private int grade_id;

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
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

                public Object getBank_realname() {
                    return bank_realname;
                }

                public void setBank_realname(Object bank_realname) {
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

                public int getGrade_id() {
                    return grade_id;
                }

                public void setGrade_id(int grade_id) {
                    this.grade_id = grade_id;
                }
            }
        }
    }
}
