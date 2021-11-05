package com.rbt.diamond.public_bean;

import java.util.List;

public class OrderCheckoutBean {

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
        private List<GoodsListBean> goods_list;
        private int order_total_num;
        private List<?> coupon_list;
        private boolean has_error;
        private String error_msg;
        private String delivery;
        private AddressBean address;
        private boolean exist_address;
        private String express_price;
        private boolean intra_region;
        private List<?> extract_shop;
        private boolean is_allow_points;
        private String is_use_points;
        private int points_money;
        private int points_bonus;
        private int pay_type;
        private SettingBean setting;
        private LastExtractBean last_extract;
        private List<String> deliverySetting;
        private String order_total_price;
        private int coupon_id;
        private int coupon_money;
        private String order_price;
        private String order_pay_price;

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public int getOrder_total_num() {
            return order_total_num;
        }

        public void setOrder_total_num(int order_total_num) {
            this.order_total_num = order_total_num;
        }

        public List<?> getCoupon_list() {
            return coupon_list;
        }

        public void setCoupon_list(List<?> coupon_list) {
            this.coupon_list = coupon_list;
        }

        public boolean isHas_error() {
            return has_error;
        }

        public void setHas_error(boolean has_error) {
            this.has_error = has_error;
        }

        public String getError_msg() {
            return error_msg;
        }

        public void setError_msg(String error_msg) {
            this.error_msg = error_msg;
        }

        public String getDelivery() {
            return delivery;
        }

        public void setDelivery(String delivery) {
            this.delivery = delivery;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public boolean isExist_address() {
            return exist_address;
        }

        public void setExist_address(boolean exist_address) {
            this.exist_address = exist_address;
        }

        public String getExpress_price() {
            return express_price;
        }

        public void setExpress_price(String express_price) {
            this.express_price = express_price;
        }

        public boolean isIntra_region() {
            return intra_region;
        }

        public void setIntra_region(boolean intra_region) {
            this.intra_region = intra_region;
        }

        public List<?> getExtract_shop() {
            return extract_shop;
        }

        public void setExtract_shop(List<?> extract_shop) {
            this.extract_shop = extract_shop;
        }

        public boolean isIs_allow_points() {
            return is_allow_points;
        }

        public void setIs_allow_points(boolean is_allow_points) {
            this.is_allow_points = is_allow_points;
        }

        public String getIs_use_points() {
            return is_use_points;
        }

        public void setIs_use_points(String is_use_points) {
            this.is_use_points = is_use_points;
        }

        public int getPoints_money() {
            return points_money;
        }

        public void setPoints_money(int points_money) {
            this.points_money = points_money;
        }

        public int getPoints_bonus() {
            return points_bonus;
        }

        public void setPoints_bonus(int points_bonus) {
            this.points_bonus = points_bonus;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public SettingBean getSetting() {
            return setting;
        }

        public void setSetting(SettingBean setting) {
            this.setting = setting;
        }

        public LastExtractBean getLast_extract() {
            return last_extract;
        }

        public void setLast_extract(LastExtractBean last_extract) {
            this.last_extract = last_extract;
        }

        public List<String> getDeliverySetting() {
            return deliverySetting;
        }

        public void setDeliverySetting(List<String> deliverySetting) {
            this.deliverySetting = deliverySetting;
        }

        public String getOrder_total_price() {
            return order_total_price;
        }

        public void setOrder_total_price(String order_total_price) {
            this.order_total_price = order_total_price;
        }

        public int getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(int coupon_id) {
            this.coupon_id = coupon_id;
        }

        public int getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(int coupon_money) {
            this.coupon_money = coupon_money;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getOrder_pay_price() {
            return order_pay_price;
        }

        public void setOrder_pay_price(String order_pay_price) {
            this.order_pay_price = order_pay_price;
        }

        public static class AddressBean {
            private int address_id;
            private String name;
            private String phone;
            private int province_id;
            private int city_id;
            private int region_id;
            private String area_id;
            private String district;
            private String detail;
            private int user_id;
            private RegionBean region;

            public int getAddress_id() {
                return address_id;
            }

            public void setAddress_id(int address_id) {
                this.address_id = address_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getProvince_id() {
                return province_id;
            }

            public void setProvince_id(int province_id) {
                this.province_id = province_id;
            }

            public int getCity_id() {
                return city_id;
            }

            public void setCity_id(int city_id) {
                this.city_id = city_id;
            }

            public int getRegion_id() {
                return region_id;
            }

            public void setRegion_id(int region_id) {
                this.region_id = region_id;
            }

            public String getArea_id() {
                return area_id;
            }

            public void setArea_id(String area_id) {
                this.area_id = area_id;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public RegionBean getRegion() {
                return region;
            }

            public void setRegion(RegionBean region) {
                this.region = region;
            }

            public static class RegionBean {
                private String province;
                private String city;
                private String region;

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getRegion() {
                    return region;
                }

                public void setRegion(String region) {
                    this.region = region;
                }
            }
        }

        public static class SettingBean {
            private List<String> delivery;
            private String points_name;
            private String points_describe;

            public List<String> getDelivery() {
                return delivery;
            }

            public void setDelivery(List<String> delivery) {
                this.delivery = delivery;
            }

            public String getPoints_name() {
                return points_name;
            }

            public void setPoints_name(String points_name) {
                this.points_name = points_name;
            }

            public String getPoints_describe() {
                return points_describe;
            }

            public void setPoints_describe(String points_describe) {
                this.points_describe = points_describe;
            }
        }

        public static class LastExtractBean {
            private String linkman;
            private String phone;

            public String getLinkman() {
                return linkman;
            }

            public void setLinkman(String linkman) {
                this.linkman = linkman;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }

        public static class GoodsListBean {
            private int goods_id;
            private String goods_name;
            private String selling_point;
            private int category_id;
            private int points_category_id;
            private int spec_type;
            private int deduct_stock_type;
            private int goods_sort;
            private int mall_no;
            private int delivery_id;
            private String points;
            private int is_points_gift;
            private int is_points_discount;
            private int is_enable_grade;
            private int is_alone_grade;
            private Object alone_grade_equity;
            private int is_ind_dealer;
            private int dealer_money_type;
            private String first_money;
            private String second_money;
            private String third_money;
            private GoodsStatusBean goods_status;
            private String goods_image;
            private GoodsSkuBean goods_sku;
            private GoodsMultiSpecBean goods_multi_spec;
            private String goods_price;
            private String total_num;
            private String spec_sku_id;
            private String total_price;
            private String total_points;
            private boolean is_user_grade;
            private int grade_ratio;
            private int grade_goods_price;
            private int grade_total_money;
            private int max_points_num;
            private int points_num;
            private int points_money;
            private int coupon_money;
            private String total_pay_price;
            private int express_price;
            private int points_bonus;
            private int goods_sales;

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

            public String getSelling_point() {
                return selling_point;
            }

            public void setSelling_point(String selling_point) {
                this.selling_point = selling_point;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public int getPoints_category_id() {
                return points_category_id;
            }

            public void setPoints_category_id(int points_category_id) {
                this.points_category_id = points_category_id;
            }

            public int getSpec_type() {
                return spec_type;
            }

            public void setSpec_type(int spec_type) {
                this.spec_type = spec_type;
            }

            public int getDeduct_stock_type() {
                return deduct_stock_type;
            }

            public void setDeduct_stock_type(int deduct_stock_type) {
                this.deduct_stock_type = deduct_stock_type;
            }

            public int getGoods_sort() {
                return goods_sort;
            }

            public void setGoods_sort(int goods_sort) {
                this.goods_sort = goods_sort;
            }

            public int getMall_no() {
                return mall_no;
            }

            public void setMall_no(int mall_no) {
                this.mall_no = mall_no;
            }

            public int getDelivery_id() {
                return delivery_id;
            }

            public void setDelivery_id(int delivery_id) {
                this.delivery_id = delivery_id;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public int getIs_points_gift() {
                return is_points_gift;
            }

            public void setIs_points_gift(int is_points_gift) {
                this.is_points_gift = is_points_gift;
            }

            public int getIs_points_discount() {
                return is_points_discount;
            }

            public void setIs_points_discount(int is_points_discount) {
                this.is_points_discount = is_points_discount;
            }

            public int getIs_enable_grade() {
                return is_enable_grade;
            }

            public void setIs_enable_grade(int is_enable_grade) {
                this.is_enable_grade = is_enable_grade;
            }

            public int getIs_alone_grade() {
                return is_alone_grade;
            }

            public void setIs_alone_grade(int is_alone_grade) {
                this.is_alone_grade = is_alone_grade;
            }

            public Object getAlone_grade_equity() {
                return alone_grade_equity;
            }

            public void setAlone_grade_equity(Object alone_grade_equity) {
                this.alone_grade_equity = alone_grade_equity;
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

            public GoodsStatusBean getGoods_status() {
                return goods_status;
            }

            public void setGoods_status(GoodsStatusBean goods_status) {
                this.goods_status = goods_status;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public GoodsSkuBean getGoods_sku() {
                return goods_sku;
            }

            public void setGoods_sku(GoodsSkuBean goods_sku) {
                this.goods_sku = goods_sku;
            }

            public GoodsMultiSpecBean getGoods_multi_spec() {
                return goods_multi_spec;
            }

            public void setGoods_multi_spec(GoodsMultiSpecBean goods_multi_spec) {
                this.goods_multi_spec = goods_multi_spec;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getTotal_num() {
                return total_num;
            }

            public void setTotal_num(String total_num) {
                this.total_num = total_num;
            }

            public String getSpec_sku_id() {
                return spec_sku_id;
            }

            public void setSpec_sku_id(String spec_sku_id) {
                this.spec_sku_id = spec_sku_id;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public String getTotal_points() {
                return total_points;
            }

            public void setTotal_points(String total_points) {
                this.total_points = total_points;
            }

            public boolean isIs_user_grade() {
                return is_user_grade;
            }

            public void setIs_user_grade(boolean is_user_grade) {
                this.is_user_grade = is_user_grade;
            }

            public int getGrade_ratio() {
                return grade_ratio;
            }

            public void setGrade_ratio(int grade_ratio) {
                this.grade_ratio = grade_ratio;
            }

            public int getGrade_goods_price() {
                return grade_goods_price;
            }

            public void setGrade_goods_price(int grade_goods_price) {
                this.grade_goods_price = grade_goods_price;
            }

            public int getGrade_total_money() {
                return grade_total_money;
            }

            public void setGrade_total_money(int grade_total_money) {
                this.grade_total_money = grade_total_money;
            }

            public int getMax_points_num() {
                return max_points_num;
            }

            public void setMax_points_num(int max_points_num) {
                this.max_points_num = max_points_num;
            }

            public int getPoints_num() {
                return points_num;
            }

            public void setPoints_num(int points_num) {
                this.points_num = points_num;
            }

            public int getPoints_money() {
                return points_money;
            }

            public void setPoints_money(int points_money) {
                this.points_money = points_money;
            }

            public int getCoupon_money() {
                return coupon_money;
            }

            public void setCoupon_money(int coupon_money) {
                this.coupon_money = coupon_money;
            }

            public String getTotal_pay_price() {
                return total_pay_price;
            }

            public void setTotal_pay_price(String total_pay_price) {
                this.total_pay_price = total_pay_price;
            }

            public int getExpress_price() {
                return express_price;
            }

            public void setExpress_price(int express_price) {
                this.express_price = express_price;
            }

            public int getPoints_bonus() {
                return points_bonus;
            }

            public void setPoints_bonus(int points_bonus) {
                this.points_bonus = points_bonus;
            }

            public int getGoods_sales() {
                return goods_sales;
            }

            public void setGoods_sales(int goods_sales) {
                this.goods_sales = goods_sales;
            }

            public static class GoodsStatusBean {
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

            public static class GoodsSkuBean {
                private int goods_sku_id;
                private int goods_id;
                private String spec_sku_id;
                private int image_id;
                private String goods_no;
                private String goods_price;
                private String line_price;
                private int stock_num;
                private int goods_sales;
                private String goods_weight;
                private String goods_attr;
                private Object image;

                public int getGoods_sku_id() {
                    return goods_sku_id;
                }

                public void setGoods_sku_id(int goods_sku_id) {
                    this.goods_sku_id = goods_sku_id;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getSpec_sku_id() {
                    return spec_sku_id;
                }

                public void setSpec_sku_id(String spec_sku_id) {
                    this.spec_sku_id = spec_sku_id;
                }

                public int getImage_id() {
                    return image_id;
                }

                public void setImage_id(int image_id) {
                    this.image_id = image_id;
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

                public int getStock_num() {
                    return stock_num;
                }

                public void setStock_num(int stock_num) {
                    this.stock_num = stock_num;
                }

                public int getGoods_sales() {
                    return goods_sales;
                }

                public void setGoods_sales(int goods_sales) {
                    this.goods_sales = goods_sales;
                }

                public String getGoods_weight() {
                    return goods_weight;
                }

                public void setGoods_weight(String goods_weight) {
                    this.goods_weight = goods_weight;
                }

                public String getGoods_attr() {
                    return goods_attr;
                }

                public void setGoods_attr(String goods_attr) {
                    this.goods_attr = goods_attr;
                }

                public Object getImage() {
                    return image;
                }

                public void setImage(Object image) {
                    this.image = image;
                }
            }

            public static class GoodsMultiSpecBean {
                private List<SpecAttrBean> spec_attr;
                private List<SpecListBean> spec_list;

                public List<SpecAttrBean> getSpec_attr() {
                    return spec_attr;
                }

                public void setSpec_attr(List<SpecAttrBean> spec_attr) {
                    this.spec_attr = spec_attr;
                }

                public List<SpecListBean> getSpec_list() {
                    return spec_list;
                }

                public void setSpec_list(List<SpecListBean> spec_list) {
                    this.spec_list = spec_list;
                }

                public static class SpecAttrBean {
                    private int group_id;
                    private String group_name;
                    private List<SpecItemsBean> spec_items;

                    public int getGroup_id() {
                        return group_id;
                    }

                    public void setGroup_id(int group_id) {
                        this.group_id = group_id;
                    }

                    public String getGroup_name() {
                        return group_name;
                    }

                    public void setGroup_name(String group_name) {
                        this.group_name = group_name;
                    }

                    public List<SpecItemsBean> getSpec_items() {
                        return spec_items;
                    }

                    public void setSpec_items(List<SpecItemsBean> spec_items) {
                        this.spec_items = spec_items;
                    }

                    public static class SpecItemsBean {
                        private int item_id;
                        private String spec_value;

                        public int getItem_id() {
                            return item_id;
                        }

                        public void setItem_id(int item_id) {
                            this.item_id = item_id;
                        }

                        public String getSpec_value() {
                            return spec_value;
                        }

                        public void setSpec_value(String spec_value) {
                            this.spec_value = spec_value;
                        }
                    }
                }

                public static class SpecListBean {
                    private int goods_sku_id;
                    private String spec_sku_id;
                    private List<?> rows;
                    private FormBean form;

                    public int getGoods_sku_id() {
                        return goods_sku_id;
                    }

                    public void setGoods_sku_id(int goods_sku_id) {
                        this.goods_sku_id = goods_sku_id;
                    }

                    public String getSpec_sku_id() {
                        return spec_sku_id;
                    }

                    public void setSpec_sku_id(String spec_sku_id) {
                        this.spec_sku_id = spec_sku_id;
                    }

                    public List<?> getRows() {
                        return rows;
                    }

                    public void setRows(List<?> rows) {
                        this.rows = rows;
                    }

                    public FormBean getForm() {
                        return form;
                    }

                    public void setForm(FormBean form) {
                        this.form = form;
                    }

                    public static class FormBean {
                        private int image_id;
                        private String image_path;
                        private String goods_no;
                        private String goods_price;
                        private String goods_weight;
                        private String line_price;
                        private int stock_num;

                        public int getImage_id() {
                            return image_id;
                        }

                        public void setImage_id(int image_id) {
                            this.image_id = image_id;
                        }

                        public String getImage_path() {
                            return image_path;
                        }

                        public void setImage_path(String image_path) {
                            this.image_path = image_path;
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

                        public String getGoods_weight() {
                            return goods_weight;
                        }

                        public void setGoods_weight(String goods_weight) {
                            this.goods_weight = goods_weight;
                        }

                        public String getLine_price() {
                            return line_price;
                        }

                        public void setLine_price(String line_price) {
                            this.line_price = line_price;
                        }

                        public int getStock_num() {
                            return stock_num;
                        }

                        public void setStock_num(int stock_num) {
                            this.stock_num = stock_num;
                        }
                    }
                }
            }
        }
    }
}
