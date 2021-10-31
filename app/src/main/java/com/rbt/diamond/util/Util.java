package com.rbt.diamond.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.text.format.DateFormat;
import android.view.Gravity;

import com.google.gson.Gson;
import com.rbt.diamond.public_bean.ResultMsgBean;
import com.sdsmdg.tastytoast.TastyToast;

import org.qinsong.lib.pay.PAY_TYPE;
import org.qinsong.lib.pay.PayAPI;
import org.qinsong.lib.pay.PayCallback;
import org.qinsong.lib.pay.PayInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {
    public static final String ws = "ws://114.115.200.77:8282";
    public static final String url = "https://diamond.zuowey.com/";

    // 吐司默认
    public static void showToast(Context ctx, String msg){
        TastyToast.makeText(ctx, msg, TastyToast.LENGTH_LONG, TastyToast.DEFAULT).setGravity(Gravity.CENTER, 0, 0);
    }

    public static void showToastInfo(Context ctx, String msg) {
        TastyToast.makeText(ctx, msg, TastyToast.LENGTH_LONG, TastyToast.INFO).setGravity(Gravity.CENTER, 0, 0);
    }

    public static void showToastSuccess(Context ctx, String msg) {
        TastyToast.makeText(ctx, msg, TastyToast.LENGTH_LONG, TastyToast.SUCCESS).setGravity(Gravity.CENTER, 0, 0);
    }

    public static void showToastError(Context ctx, String msg) {
        TastyToast.makeText(ctx, msg, TastyToast.LENGTH_LONG, TastyToast.ERROR).setGravity(Gravity.CENTER, 0, 0);
    }

    public static void showToastWarning(Context ctx, String msg) {
        TastyToast.makeText(ctx, msg, TastyToast.LENGTH_LONG, TastyToast.WARNING).setGravity(Gravity.CENTER, 0, 0);
    }

    /**
     * 根据当前日期获得是周几
     * time=yyyy-MM-dd
     * @return
     */
    public static String getWeek(String time) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int wek=c.get(Calendar.DAY_OF_WEEK);

        if (wek == 1) {
            Week += "周日";
        }
        if (wek == 2) {
            Week += "周一";
        }
        if (wek == 3) {
            Week += "周二";
        }
        if (wek == 4) {
            Week += "周三";
        }
        if (wek == 5) {
            Week += "周四";
        }
        if (wek == 6) {
            Week += "周五";
        }
        if (wek == 7) {
            Week += "周六";
        }
        return Week;
    }

    public static String conversionTime(Long time, String format) {
        // yyyy-MM-dd HH:mm:ss
        return DateFormat.format(format, time).toString();
    }

    public static String dateToStamp(String s, String format) throws ParseException {
        String res;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return String.valueOf(ts);
    }

    public static ResultMsgBean ResultFunction(String response){
        Gson gson = new Gson();
        ResultMsgBean resultMsgBean = gson.fromJson(response, ResultMsgBean.class);
        return resultMsgBean;
    }

    public static String getVersionName(Context context) throws Exception
    {
        // 获取package manager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
        String version = packInfo.versionName;
        return version;
    }

    public static String getToken(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sharedPreferences.getString("token","");
    }

    public static int getUserId(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("user_id",0);
    }

    public static void callPhone(Context context, String str) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + str));
        context.startActivity(intent);
    }

    public static void dateDiff(String startTime, String endTime, String format) {
        // 按照传入的格式生成一个simple date format对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数long diff;try {
        // 获得两个时间的毫秒时间差异
        try {
            long diff = sd.parse(endTime).getTime()
                    - sd.parse(startTime).getTime();
            long day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒//输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟"
                    + sec + "秒。");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //启动支付
    public static void paySdk(Activity context, PayInfo payInfo, PAY_TYPE pay_type) {
        PayAPI.get(context, pay_type).pay(payInfo, new PayCallback() {
            @Override
            public void onComplete(PAY_TYPE payType, String result) {
                //同步支付结果成功
                Util.showToastSuccess(context, "支付成功");
            }

            @Override
            public void onFail(PAY_TYPE payType, String msg) {
                System.out.println("支付失败");
            }

            @Override
            public void onCancel(PAY_TYPE payType) {
                System.out.println("支付取消");
            }
        });
    }

    public static String getAppVersionCode(Context context) {
        int versioncode = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            // versionName = pi.versionName;
            versioncode = pi.versionCode;
        } catch (Exception e) {
        }
        return versioncode + "";
    }

    private String getMipmapToUri(Context context, int resId) {

        Resources r = context.getResources();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resId) + "/"
                + r.getResourceTypeName(resId) + "/"
                + r.getResourceEntryName(resId));

        return uri.toString();
    }

    public static String getCustomUrl(String customUrl){
        return url + customUrl;
    }
}
