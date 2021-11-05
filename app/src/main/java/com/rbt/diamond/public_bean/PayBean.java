package com.rbt.diamond.public_bean;

import java.io.Serializable;

public class PayBean implements Serializable {

    /**
     * code : 1
     * msg : 获取成功
     * data : alipay_sdk=alipay-sdk-php-20180705&app_id=2021002156648999&biz_content=%7B%22body%22%3A%22%E6%B5%8B%E8%AF%95%22%2C%22subject%22%3A%22%E6%B5%8B%E8%AF%95%22%2C%22out_trade_no%22%3A1692689922%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A1%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fsale.itaustin.cn%2Fnotice_consignment.php&sign_type=RSA2&timestamp=2021-07-17+12%3A51%3A04&version=1.0&sign=dT0tP744NNDlZVebQqNSnIJVxCFBvxpVNZY68ZIM0CxFcou44nwpYvbRcbjU99CHCicmN8KnL1UFLXMa4HtKvrFPB045qWntpkAAneUJP48dtB%2FNav5LMmqfxHjQKOjeARcqn9QaNziGIfGVs2XyOYbA5nQo1OMGLC5XdWiHMAamFn1Wy4vMIwV7ttoLqVXWmm9g0UyIiU9weicMSmHcCYZ8241DU%2Fdw21e8GSRx%2BMYMIZiVY2nr7ttO5zjexuDnjG%2FANh%2FTkjwShk5IUmccuSxFMRWK%2BYeYtiJ64%2Fs33QGDaBZatPvlVzA6fHRYOa5m3dvcAfRrBKK0JOWsCHKJog%3D%3D
     */

    private Integer code;
    private String msg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
