package com.rbt.diamond.public_bean;

import java.util.List;

public class GoodsDetailBean {

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
        private DetailBean detail;
        private int cart_total_num;
        private SpecDataBean specData;

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public int getCart_total_num() {
            return cart_total_num;
        }

        public void setCart_total_num(int cart_total_num) {
            this.cart_total_num = cart_total_num;
        }

        public SpecDataBean getSpecData() {
            return specData;
        }

        public void setSpecData(SpecDataBean specData) {
            this.specData = specData;
        }

        public static class DetailBean {
            private int goods_id;
            private String goods_name;
            private String selling_point;
            private int category_id;
            private int points_category_id;
            private int spec_type;
            private int deduct_stock_type;
            private String content;
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
            private int comment_data_count;
            private String goods_image;
            private GoodsSkuBean goods_sku;
            private boolean is_user_grade;
            private GoodsMultiSpecBean goods_multi_spec;
            private CategoryBean category;
            private List<ImageBean> image;
            private List<SkuBean> sku;
            private List<CommentDataBean> comment_data;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public int getComment_data_count() {
                return comment_data_count;
            }

            public void setComment_data_count(int comment_data_count) {
                this.comment_data_count = comment_data_count;
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

            public boolean isIs_user_grade() {
                return is_user_grade;
            }

            public void setIs_user_grade(boolean is_user_grade) {
                this.is_user_grade = is_user_grade;
            }

            public GoodsMultiSpecBean getGoods_multi_spec() {
                return goods_multi_spec;
            }

            public void setGoods_multi_spec(GoodsMultiSpecBean goods_multi_spec) {
                this.goods_multi_spec = goods_multi_spec;
            }

            public CategoryBean getCategory() {
                return category;
            }

            public void setCategory(CategoryBean category) {
                this.category = category;
            }

            public List<ImageBean> getImage() {
                return image;
            }

            public void setImage(List<ImageBean> image) {
                this.image = image;
            }

            public List<SkuBean> getSku() {
                return sku;
            }

            public void setSku(List<SkuBean> sku) {
                this.sku = sku;
            }

            public List<CommentDataBean> getComment_data() {
                return comment_data;
            }

            public void setComment_data(List<CommentDataBean> comment_data) {
                this.comment_data = comment_data;
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
                        private Boolean isSelected = false;

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

                        public Boolean getSelected() {
                            return isSelected;
                        }

                        public void setSelected(Boolean selected) {
                            isSelected = selected;
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

            public static class CategoryBean {
                private int category_id;
                private String name;
                private int parent_id;
                private int image_id;
                private int sort;
                private String create_time;

                public int getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(int category_id) {
                    this.category_id = category_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getParent_id() {
                    return parent_id;
                }

                public void setParent_id(int parent_id) {
                    this.parent_id = parent_id;
                }

                public int getImage_id() {
                    return image_id;
                }

                public void setImage_id(int image_id) {
                    this.image_id = image_id;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }
            }

            public static class ImageBean {
                private int id;
                private int goods_id;
                private int image_id;
                private String file_path;
                private String file_name;
                private String file_url;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public int getImage_id() {
                    return image_id;
                }

                public void setImage_id(int image_id) {
                    this.image_id = image_id;
                }

                public String getFile_path() {
                    return file_path;
                }

                public void setFile_path(String file_path) {
                    this.file_path = file_path;
                }

                public String getFile_name() {
                    return file_name;
                }

                public void setFile_name(String file_name) {
                    this.file_name = file_name;
                }

                public String getFile_url() {
                    return file_url;
                }

                public void setFile_url(String file_url) {
                    this.file_url = file_url;
                }
            }

            public static class SkuBean {
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

                public Object getImage() {
                    return image;
                }

                public void setImage(Object image) {
                    this.image = image;
                }
            }

            public static class CommentDataBean {
                private int comment_id;
                private int score;
                private String content;
                private int is_picture;
                private int user_id;
                private int wxapp_id;
                private String create_time;
                private UserBean user;

                public int getComment_id() {
                    return comment_id;
                }

                public void setComment_id(int comment_id) {
                    this.comment_id = comment_id;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getIs_picture() {
                    return is_picture;
                }

                public void setIs_picture(int is_picture) {
                    this.is_picture = is_picture;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
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

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public static class UserBean {
                    private int user_id;
                    private String nickName;
                    private String avatarUrl;

                    public int getUser_id() {
                        return user_id;
                    }

                    public void setUser_id(int user_id) {
                        this.user_id = user_id;
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
                }
            }
        }

        public static class SpecDataBean {
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
                private List<DetailBean.GoodsMultiSpecBean.SpecAttrBean.SpecItemsBean> spec_items;

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

                public List<DetailBean.GoodsMultiSpecBean.SpecAttrBean.SpecItemsBean> getSpec_items() {
                    return spec_items;
                }

                public void setSpec_items(List<DetailBean.GoodsMultiSpecBean.SpecAttrBean.SpecItemsBean> spec_items) {
                    this.spec_items = spec_items;
                }
            }

            public static class SpecListBean {
                private int goods_sku_id;
                private String spec_sku_id;
                private List<?> rows;
                private DetailBean.GoodsMultiSpecBean.SpecListBean.FormBean form;

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

                public DetailBean.GoodsMultiSpecBean.SpecListBean.FormBean getForm() {
                    return form;
                }

                public void setForm(DetailBean.GoodsMultiSpecBean.SpecListBean.FormBean form) {
                    this.form = form;
                }
            }
        }
    }
}
