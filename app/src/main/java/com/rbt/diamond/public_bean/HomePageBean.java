package com.rbt.diamond.public_bean;

import java.util.List;

public class HomePageBean {

    private int code;
    private String msg;
    private DataListBean dataList;

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

    public DataListBean getDataList() {
        return dataList;
    }

    public void setDataList(DataListBean dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        private SearchBean search;
        private BannerBean banner;
        private GoodsBean goods;

        public SearchBean getSearch() {
            return search;
        }

        public void setSearch(SearchBean search) {
            this.search = search;
        }

        public BannerBean getBanner() {
            return banner;
        }

        public void setBanner(BannerBean banner) {
            this.banner = banner;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public static class SearchBean {
            private String name;
            private String type;
            private ParamsBean params;
            private StyleBean style;
            private String id;

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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class ParamsBean {
                private String placeholder;

                public String getPlaceholder() {
                    return placeholder;
                }

                public void setPlaceholder(String placeholder) {
                    this.placeholder = placeholder;
                }
            }

            public static class StyleBean {
                private String textAlign;
                private String searchStyle;

                public String getTextAlign() {
                    return textAlign;
                }

                public void setTextAlign(String textAlign) {
                    this.textAlign = textAlign;
                }

                public String getSearchStyle() {
                    return searchStyle;
                }

                public void setSearchStyle(String searchStyle) {
                    this.searchStyle = searchStyle;
                }
            }
        }

        public static class BannerBean {
            private String name;
            private String type;
            private StyleBean style;
            private ParamsBean params;
            private List<DataBean> data;

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

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
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

        public static class GoodsBean {
            private String name;
            private String type;
            private ParamsBean params;
            private StyleBean style;
            private List<DefaultDataBean> defaultData;
            private List<DataBean> data;
            private List<GoodsDataBean> goodsData;

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

            public List<DefaultDataBean> getDefaultData() {
                return defaultData;
            }

            public void setDefaultData(List<DefaultDataBean> defaultData) {
                this.defaultData = defaultData;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public List<GoodsDataBean> getGoodsData() {
                return goodsData;
            }

            public void setGoodsData(List<GoodsDataBean> goodsData) {
                this.goodsData = goodsData;
            }

            public static class ParamsBean {
                private String source;
                private AutoBean auto;

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
                private ShowBean show;

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
    }
}
