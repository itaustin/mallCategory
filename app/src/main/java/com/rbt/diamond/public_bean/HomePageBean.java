package com.rbt.diamond.public_bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomePageBean {

    private int code;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        private PageBean page;
        private ItemsBean items;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public ItemsBean getItems() {
            return items;
        }

        public void setItems(ItemsBean items) {
            this.items = items;
        }

        public static class PageBean {
            private String type;
            private String name;
            private ParamsBean params;
            private StyleBean style;
            private String id;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public ParamsBean getParams() {
                return params;
            }

            public void setParams(ParamsBean params) {
                this.params = params;
            }

            public StyleBean getStyle() {
                return style;
            }

            public void setStyle(StyleBean style) {
                this.style = style;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class ParamsBean {
                private String name;
                private String title;
                private String share_title;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getShare_title() {
                    return share_title;
                }

                public void setShare_title(String share_title) {
                    this.share_title = share_title;
                }
            }

            public static class StyleBean {
                private String titleTextColor;
                private String titleBackgroundColor;

                public String getTitleTextColor() {
                    return titleTextColor;
                }

                public void setTitleTextColor(String titleTextColor) {
                    this.titleTextColor = titleTextColor;
                }

                public String getTitleBackgroundColor() {
                    return titleBackgroundColor;
                }

                public void setTitleBackgroundColor(String titleBackgroundColor) {
                    this.titleBackgroundColor = titleBackgroundColor;
                }
            }
        }

        public static class ItemsBean {
            @SerializedName("0")
            private _$0Bean _$0;
            @SerializedName("1")
            private _$1Bean _$1;
            @SerializedName("2")
            private _$2Bean _$2;
            @SerializedName("3")
            private _$3Bean _$3;
            private PageBean page;

            public _$0Bean get_$0() {
                return _$0;
            }

            public void set_$0(_$0Bean _$0) {
                this._$0 = _$0;
            }

            public _$1Bean get_$1() {
                return _$1;
            }

            public void set_$1(_$1Bean _$1) {
                this._$1 = _$1;
            }

            public _$2Bean get_$2() {
                return _$2;
            }

            public void set_$2(_$2Bean _$2) {
                this._$2 = _$2;
            }

            public _$3Bean get_$3() {
                return _$3;
            }

            public void set_$3(_$3Bean _$3) {
                this._$3 = _$3;
            }

            public PageBean getPage() {
                return page;
            }

            public void setPage(PageBean page) {
                this.page = page;
            }

            public static class _$0Bean {
                private String name;
                private String type;
                private DataBeanX.ItemsBean._$0Bean.StyleBean style;
                private DataBeanX.ItemsBean._$0Bean.ParamsBean params;
                private List<DataBeanX.ItemsBean._$0Bean.DataBean> data;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public StyleBean getStyle() {
                    return style;
                }

                public void setStyle(StyleBean style) {
                    this.style = style;
                }

                public ParamsBean getParams() {
                    return params;
                }

                public void setParams(ParamsBean params) {
                    this.params = params;
                }

                public List<DataBeanX.ItemsBean._$0Bean.DataBean> getData() {
                    return data;
                }

                public void setData(List<DataBeanX.ItemsBean._$0Bean.DataBean> data) {
                    this.data = data;
                }

                public static class StyleBean {
                    private String btnColor;
                    private String btnShape;

                    public String getBtnColor() {
                        return btnColor;
                    }

                    public void setBtnColor(String btnColor) {
                        this.btnColor = btnColor;
                    }

                    public String getBtnShape() {
                        return btnShape;
                    }

                    public void setBtnShape(String btnShape) {
                        this.btnShape = btnShape;
                    }
                }

                public static class ParamsBean {
                    private String interval;

                    public String getInterval() {
                        return interval;
                    }

                    public void setInterval(String interval) {
                        this.interval = interval;
                    }
                }

                public static class DataBean {
                    private String imgUrl;
                    private String linkUrl;

                    public String getImgUrl() {
                        return imgUrl;
                    }

                    public void setImgUrl(String imgUrl) {
                        this.imgUrl = imgUrl;
                    }

                    public String getLinkUrl() {
                        return linkUrl;
                    }

                    public void setLinkUrl(String linkUrl) {
                        this.linkUrl = linkUrl;
                    }
                }
            }

            public static class _$1Bean {
                private String name;
                private String type;
                private ParamsBean params;
                private StyleBean style;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public ParamsBean getParams() {
                    return params;
                }

                public void setParams(ParamsBean params) {
                    this.params = params;
                }

                public StyleBean getStyle() {
                    return style;
                }

                public void setStyle(StyleBean style) {
                    this.style = style;
                }

                public static class ParamsBean {
                    private String text;
                    private String icon;

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }
                }

                public static class StyleBean {
                    private String paddingTop;
                    private String background;
                    private String textColor;

                    public String getPaddingTop() {
                        return paddingTop;
                    }

                    public void setPaddingTop(String paddingTop) {
                        this.paddingTop = paddingTop;
                    }

                    public String getBackground() {
                        return background;
                    }

                    public void setBackground(String background) {
                        this.background = background;
                    }

                    public String getTextColor() {
                        return textColor;
                    }

                    public void setTextColor(String textColor) {
                        this.textColor = textColor;
                    }
                }
            }

            public static class _$2Bean {
                private String name;
                private String type;
                private DataBeanX.ItemsBean._$2Bean.ParamsBean params;
                private DataBeanX.ItemsBean._$2Bean.StyleBean style;
                private List<DataBeanX.ItemsBean._$2Bean.DefaultDataBean> defaultData;
                private List<DataBeanX.ItemsBean._$2Bean.DataBean> data;
                private List<DataBeanX.ItemsBean._$2Bean.GoodsDataBean> goodsData;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public ParamsBean getParams() {
                    return params;
                }

                public void setParams(ParamsBean params) {
                    this.params = params;
                }

                public StyleBean getStyle() {
                    return style;
                }

                public void setStyle(StyleBean style) {
                    this.style = style;
                }

                public List<DataBeanX.ItemsBean._$2Bean.DefaultDataBean> getDefaultData() {
                    return defaultData;
                }

                public void setDefaultData(List<DataBeanX.ItemsBean._$2Bean.DefaultDataBean> defaultData) {
                    this.defaultData = defaultData;
                }

                public List<DataBeanX.ItemsBean._$2Bean.DataBean> getData() {
                    return data;
                }

                public void setData(List<DataBeanX.ItemsBean._$2Bean.DataBean> data) {
                    this.data = data;
                }

                public List<DataBeanX.ItemsBean._$2Bean.GoodsDataBean> getGoodsData() {
                    return goodsData;
                }

                public void setGoodsData(List<DataBeanX.ItemsBean._$2Bean.GoodsDataBean> goodsData) {
                    this.goodsData = goodsData;
                }

                public static class ParamsBean {
                    private String source;
                    private DataBeanX.ItemsBean._$2Bean.ParamsBean.AutoBean auto;

                    public String getSource() {
                        return source;
                    }

                    public void setSource(String source) {
                        this.source = source;
                    }

                    public AutoBean getAuto() {
                        return auto;
                    }

                    public void setAuto(AutoBean auto) {
                        this.auto = auto;
                    }

                    public static class AutoBean {
                        private int category;
                        private String goodsSort;
                        private String showNum;

                        public int getCategory() {
                            return category;
                        }

                        public void setCategory(int category) {
                            this.category = category;
                        }

                        public String getGoodsSort() {
                            return goodsSort;
                        }

                        public void setGoodsSort(String goodsSort) {
                            this.goodsSort = goodsSort;
                        }

                        public String getShowNum() {
                            return showNum;
                        }

                        public void setShowNum(String showNum) {
                            this.showNum = showNum;
                        }
                    }
                }

                public static class StyleBean {
                    private String background;
                    private String display;
                    private String column;
                    private DataBeanX.ItemsBean._$2Bean.StyleBean.ShowBean show;

                    public String getBackground() {
                        return background;
                    }

                    public void setBackground(String background) {
                        this.background = background;
                    }

                    public String getDisplay() {
                        return display;
                    }

                    public void setDisplay(String display) {
                        this.display = display;
                    }

                    public String getColumn() {
                        return column;
                    }

                    public void setColumn(String column) {
                        this.column = column;
                    }

                    public ShowBean getShow() {
                        return show;
                    }

                    public void setShow(ShowBean show) {
                        this.show = show;
                    }

                    public static class ShowBean {
                        private String goodsName;
                        private String goodsPrice;
                        private String linePrice;
                        private String sellingPoint;
                        private String goodsSales;

                        public String getGoodsName() {
                            return goodsName;
                        }

                        public void setGoodsName(String goodsName) {
                            this.goodsName = goodsName;
                        }

                        public String getGoodsPrice() {
                            return goodsPrice;
                        }

                        public void setGoodsPrice(String goodsPrice) {
                            this.goodsPrice = goodsPrice;
                        }

                        public String getLinePrice() {
                            return linePrice;
                        }

                        public void setLinePrice(String linePrice) {
                            this.linePrice = linePrice;
                        }

                        public String getSellingPoint() {
                            return sellingPoint;
                        }

                        public void setSellingPoint(String sellingPoint) {
                            this.sellingPoint = sellingPoint;
                        }

                        public String getGoodsSales() {
                            return goodsSales;
                        }

                        public void setGoodsSales(String goodsSales) {
                            this.goodsSales = goodsSales;
                        }
                    }
                }

                public static class DefaultDataBean {
                    private String goods_name;
                    private String image;
                    private String goods_price;
                    private String line_price;
                    private String selling_point;
                    private String goods_sales;

                    public String getGoods_name() {
                        return goods_name;
                    }

                    public void setGoods_name(String goods_name) {
                        this.goods_name = goods_name;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
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

                    public String getSelling_point() {
                        return selling_point;
                    }

                    public void setSelling_point(String selling_point) {
                        this.selling_point = selling_point;
                    }

                    public String getGoods_sales() {
                        return goods_sales;
                    }

                    public void setGoods_sales(String goods_sales) {
                        this.goods_sales = goods_sales;
                    }
                }

                public static class DataBean {
                    private String goods_name;
                    private String image;
                    private String goods_price;
                    private String line_price;
                    private String selling_point;
                    private String goods_sales;
                    private boolean is_default;

                    public String getGoods_name() {
                        return goods_name;
                    }

                    public void setGoods_name(String goods_name) {
                        this.goods_name = goods_name;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
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

                    public String getSelling_point() {
                        return selling_point;
                    }

                    public void setSelling_point(String selling_point) {
                        this.selling_point = selling_point;
                    }

                    public String getGoods_sales() {
                        return goods_sales;
                    }

                    public void setGoods_sales(String goods_sales) {
                        this.goods_sales = goods_sales;
                    }

                    public boolean isIs_default() {
                        return is_default;
                    }

                    public void setIs_default(boolean is_default) {
                        this.is_default = is_default;
                    }
                }

                public static class GoodsDataBean {
                    private int goods_id;
                    private String goods_name;
                    private String selling_point;
                    private String image;
                    private String goods_image;
                    private String goods_price;
                    private String line_price;
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

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                    public String getGoods_image() {
                        return goods_image;
                    }

                    public void setGoods_image(String goods_image) {
                        this.goods_image = goods_image;
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

                    public int getGoods_sales() {
                        return goods_sales;
                    }

                    public void setGoods_sales(int goods_sales) {
                        this.goods_sales = goods_sales;
                    }
                }
            }

            public static class _$3Bean {
                private String name;
                private String type;
                private DataBeanX.ItemsBean._$3Bean.ParamsBean params;
                private DataBeanX.ItemsBean._$3Bean.StyleBean style;
                private List<DataBeanX.ItemsBean._$3Bean.DefaultDataBean> defaultData;
                private List<DataBeanX.ItemsBean._$3Bean.DataBean> data;
                private List<DataBeanX.ItemsBean._$3Bean.GoodsDataBean> goodsData;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public ParamsBean getParams() {
                    return params;
                }

                public void setParams(ParamsBean params) {
                    this.params = params;
                }

                public StyleBean getStyle() {
                    return style;
                }

                public void setStyle(StyleBean style) {
                    this.style = style;
                }

                public List<DataBeanX.ItemsBean._$3Bean.DefaultDataBean> getDefaultData() {
                    return defaultData;
                }

                public void setDefaultData(List<DataBeanX.ItemsBean._$3Bean.DefaultDataBean> defaultData) {
                    this.defaultData = defaultData;
                }

                public List<DataBeanX.ItemsBean._$3Bean.DataBean> getData() {
                    return data;
                }

                public void setData(List<DataBeanX.ItemsBean._$3Bean.DataBean> data) {
                    this.data = data;
                }

                public List<DataBeanX.ItemsBean._$3Bean.GoodsDataBean> getGoodsData() {
                    return goodsData;
                }

                public void setGoodsData(List<DataBeanX.ItemsBean._$3Bean.GoodsDataBean> goodsData) {
                    this.goodsData = goodsData;
                }

                public static class ParamsBean {
                    private String source;
                    private DataBeanX.ItemsBean._$3Bean.ParamsBean.AutoBean auto;

                    public String getSource() {
                        return source;
                    }

                    public void setSource(String source) {
                        this.source = source;
                    }

                    public AutoBean getAuto() {
                        return auto;
                    }

                    public void setAuto(AutoBean auto) {
                        this.auto = auto;
                    }

                    public static class AutoBean {
                        private int category;
                        private String goodsSort;
                        private int showNum;

                        public int getCategory() {
                            return category;
                        }

                        public void setCategory(int category) {
                            this.category = category;
                        }

                        public String getGoodsSort() {
                            return goodsSort;
                        }

                        public void setGoodsSort(String goodsSort) {
                            this.goodsSort = goodsSort;
                        }

                        public int getShowNum() {
                            return showNum;
                        }

                        public void setShowNum(int showNum) {
                            this.showNum = showNum;
                        }
                    }
                }

                public static class StyleBean {
                    private String background;
                    private String display;
                    private String column;
                    private DataBeanX.ItemsBean._$3Bean.StyleBean.ShowBean show;

                    public String getBackground() {
                        return background;
                    }

                    public void setBackground(String background) {
                        this.background = background;
                    }

                    public String getDisplay() {
                        return display;
                    }

                    public void setDisplay(String display) {
                        this.display = display;
                    }

                    public String getColumn() {
                        return column;
                    }

                    public void setColumn(String column) {
                        this.column = column;
                    }

                    public ShowBean getShow() {
                        return show;
                    }

                    public void setShow(ShowBean show) {
                        this.show = show;
                    }

                    public static class ShowBean {
                        private String goodsName;
                        private String goodsPrice;
                        private String linePrice;
                        private String sellingPoint;
                        private String goodsSales;

                        public String getGoodsName() {
                            return goodsName;
                        }

                        public void setGoodsName(String goodsName) {
                            this.goodsName = goodsName;
                        }

                        public String getGoodsPrice() {
                            return goodsPrice;
                        }

                        public void setGoodsPrice(String goodsPrice) {
                            this.goodsPrice = goodsPrice;
                        }

                        public String getLinePrice() {
                            return linePrice;
                        }

                        public void setLinePrice(String linePrice) {
                            this.linePrice = linePrice;
                        }

                        public String getSellingPoint() {
                            return sellingPoint;
                        }

                        public void setSellingPoint(String sellingPoint) {
                            this.sellingPoint = sellingPoint;
                        }

                        public String getGoodsSales() {
                            return goodsSales;
                        }

                        public void setGoodsSales(String goodsSales) {
                            this.goodsSales = goodsSales;
                        }
                    }
                }

                public static class DefaultDataBean {
                    private String goods_name;
                    private String image;
                    private String goods_price;
                    private String line_price;
                    private String selling_point;
                    private String goods_sales;

                    public String getGoods_name() {
                        return goods_name;
                    }

                    public void setGoods_name(String goods_name) {
                        this.goods_name = goods_name;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
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

                    public String getSelling_point() {
                        return selling_point;
                    }

                    public void setSelling_point(String selling_point) {
                        this.selling_point = selling_point;
                    }

                    public String getGoods_sales() {
                        return goods_sales;
                    }

                    public void setGoods_sales(String goods_sales) {
                        this.goods_sales = goods_sales;
                    }
                }

                public static class DataBean {
                    private String goods_name;
                    private String image;
                    private String goods_price;
                    private String line_price;
                    private String selling_point;
                    private String goods_sales;
                    private boolean is_default;

                    public String getGoods_name() {
                        return goods_name;
                    }

                    public void setGoods_name(String goods_name) {
                        this.goods_name = goods_name;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
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

                    public String getSelling_point() {
                        return selling_point;
                    }

                    public void setSelling_point(String selling_point) {
                        this.selling_point = selling_point;
                    }

                    public String getGoods_sales() {
                        return goods_sales;
                    }

                    public void setGoods_sales(String goods_sales) {
                        this.goods_sales = goods_sales;
                    }

                    public boolean isIs_default() {
                        return is_default;
                    }

                    public void setIs_default(boolean is_default) {
                        this.is_default = is_default;
                    }
                }

                public static class GoodsDataBean {
                    private int goods_id;
                    private String goods_name;
                    private String selling_point;
                    private String image;
                    private String goods_image;
                    private String goods_price;
                    private String line_price;
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

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                    public String getGoods_image() {
                        return goods_image;
                    }

                    public void setGoods_image(String goods_image) {
                        this.goods_image = goods_image;
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

                    public int getGoods_sales() {
                        return goods_sales;
                    }

                    public void setGoods_sales(int goods_sales) {
                        this.goods_sales = goods_sales;
                    }
                }
            }

            public static class PageBean {
                private String type;
                private String name;
                private ParamsBean params;
                private StyleBean style;
                private String id;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public ParamsBean getParams() {
                    return params;
                }

                public void setParams(ParamsBean params) {
                    this.params = params;
                }

                public StyleBean getStyle() {
                    return style;
                }

                public void setStyle(StyleBean style) {
                    this.style = style;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public static class ParamsBean {
                    private String name;
                    private String title;
                    private String share_title;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getShare_title() {
                        return share_title;
                    }

                    public void setShare_title(String share_title) {
                        this.share_title = share_title;
                    }
                }

                public static class StyleBean {
                    private String titleTextColor;
                    private String titleBackgroundColor;

                    public String getTitleTextColor() {
                        return titleTextColor;
                    }

                    public void setTitleTextColor(String titleTextColor) {
                        this.titleTextColor = titleTextColor;
                    }

                    public String getTitleBackgroundColor() {
                        return titleBackgroundColor;
                    }

                    public void setTitleBackgroundColor(String titleBackgroundColor) {
                        this.titleBackgroundColor = titleBackgroundColor;
                    }
                }
            }
        }
    }
}
