package com.rbt.diamond.public_bean;

import java.io.Serializable;

public class UploadImageBean implements Serializable {

    /**
     * code : 1
     * msg : 上传成功
     * data : {"storage":"local","file_url":"https://sale.itaustin.cn","file_name":"20210719/ded54f6e77c3907f7d61e7c2c11b64d2.jpg","file_source_name":"wx_camera_1626579705640.jpg","file_size":1117365,"file_type":"image/jpeg","extension":"jpg","is_user":1,"file_id":"14695","file_path":"https://sale.itaustin.cn/uploads/20210719/ded54f6e77c3907f7d61e7c2c11b64d2.jpg"}
     * url : https://sale.itaustin.cn/uploads/20210719/ded54f6e77c3907f7d61e7c2c11b64d2.jpg
     */

    private Integer code;
    private String msg;
    private DataBean data;
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class DataBean implements Serializable {
        /**
         * storage : local
         * file_url : https://sale.itaustin.cn
         * file_name : 20210719/ded54f6e77c3907f7d61e7c2c11b64d2.jpg
         * file_source_name : wx_camera_1626579705640.jpg
         * file_size : 1117365
         * file_type : image/jpeg
         * extension : jpg
         * is_user : 1
         * file_id : 14695
         * file_path : https://sale.itaustin.cn/uploads/20210719/ded54f6e77c3907f7d61e7c2c11b64d2.jpg
         */

        private String storage;
        private String file_url;
        private String file_name;
        private String file_source_name;
        private Integer file_size;
        private String file_type;
        private String extension;
        private Integer is_user;
        private String file_id;
        private String file_path;

        public String getStorage() {
            return storage;
        }

        public void setStorage(String storage) {
            this.storage = storage;
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

        public String getFile_source_name() {
            return file_source_name;
        }

        public void setFile_source_name(String file_source_name) {
            this.file_source_name = file_source_name;
        }

        public Integer getFile_size() {
            return file_size;
        }

        public void setFile_size(Integer file_size) {
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

        public Integer getIs_user() {
            return is_user;
        }

        public void setIs_user(Integer is_user) {
            this.is_user = is_user;
        }

        public String getFile_id() {
            return file_id;
        }

        public void setFile_id(String file_id) {
            this.file_id = file_id;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }
    }
}
