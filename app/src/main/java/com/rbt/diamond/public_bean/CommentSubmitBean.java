package com.rbt.diamond.public_bean;

import java.util.List;

public class CommentSubmitBean {
    protected int order_id;

    protected String wxapp_id;

    protected String token;

    private List<FormDataBean> formData;

    public List<FormDataBean> getFormData() {
        return formData;
    }

    public void setFormData(List<FormDataBean> formData) {
        this.formData = formData;
    }

    public static class FormDataBean {
        private int goods_id;
        private int order_goods_id;
        private int score = 0;
        private String content;
        private List<?> image_list;
        private List<?> uploaded;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getOrder_goods_id() {
            return order_goods_id;
        }

        public void setOrder_goods_id(int order_goods_id) {
            this.order_goods_id = order_goods_id;
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

        public List<?> getImage_list() {
            return image_list;
        }

        public void setImage_list(List<?> image_list) {
            this.image_list = image_list;
        }

        public List<?> getUploaded() {
            return uploaded;
        }

        public void setUploaded(List<?> uploaded) {
            this.uploaded = uploaded;
        }
    }
}
