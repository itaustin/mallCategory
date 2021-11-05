package com.rbt.diamond.public_bean;

import java.io.Serializable;
import java.util.List;

public class AddressDetailBean implements Serializable {

    /**
     * code : 1
     * msg : success
     * data : {"detail":{"address_id":10689,"name":"郑义","phone":"17786492326","province_id":1709,"city_id":1710,"region_id":1716,"area_id":"","district":"","detail":"怡景雅居3栋2单元302","user_id":10001,"region":{"province":"湖北省","city":"武汉市","region":"青山区"}},"region":["湖北省","武汉市","青山区"]}
     */

    private Integer code;
    private String msg;
    private DataBean data;

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

    public static class DataBean implements Serializable {
        /**
         * detail : {"address_id":10689,"name":"郑义","phone":"17786492326","province_id":1709,"city_id":1710,"region_id":1716,"area_id":"","district":"","detail":"怡景雅居3栋2单元302","user_id":10001,"region":{"province":"湖北省","city":"武汉市","region":"青山区"}}
         * region : ["湖北省","武汉市","青山区"]
         */

        private DetailBean detail;
        private List<String> region;

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public List<String> getRegion() {
            return region;
        }

        public void setRegion(List<String> region) {
            this.region = region;
        }

        public static class DetailBean implements Serializable {
            /**
             * address_id : 10689
             * name : 郑义
             * phone : 17786492326
             * province_id : 1709
             * city_id : 1710
             * region_id : 1716
             * area_id :
             * district :
             * detail : 怡景雅居3栋2单元302
             * user_id : 10001
             * region : {"province":"湖北省","city":"武汉市","region":"青山区"}
             */

            private Integer address_id;
            private String name;
            private String phone;
            private Integer province_id;
            private Integer city_id;
            private Integer region_id;
            private String area_id;
            private String district;
            private String detail;
            private Integer user_id;
            private RegionBean region;

            public Integer getAddress_id() {
                return address_id;
            }

            public void setAddress_id(Integer address_id) {
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

            public Integer getProvince_id() {
                return province_id;
            }

            public void setProvince_id(Integer province_id) {
                this.province_id = province_id;
            }

            public Integer getCity_id() {
                return city_id;
            }

            public void setCity_id(Integer city_id) {
                this.city_id = city_id;
            }

            public Integer getRegion_id() {
                return region_id;
            }

            public void setRegion_id(Integer region_id) {
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

            public Integer getUser_id() {
                return user_id;
            }

            public void setUser_id(Integer user_id) {
                this.user_id = user_id;
            }

            public RegionBean getRegion() {
                return region;
            }

            public void setRegion(RegionBean region) {
                this.region = region;
            }

            public static class RegionBean implements Serializable {
                /**
                 * province : 湖北省
                 * city : 武汉市
                 * region : 青山区
                 */

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
    }
}
