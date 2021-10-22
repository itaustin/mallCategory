package com.rbt.diamond;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.multidex.MultiDex;

import com.hjq.bar.TitleBar;
import com.hjq.bar.initializer.LightBarInitializer;
import com.rbt.diamond.DensityHelper;
import com.rbt.diamond.im.JWebSocketClient;
import com.rbt.diamond.im.JWebSocketClientService;
import com.vector.update_app.UpdateAppManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {

    private static final float DESIGN_WIDTH = 360;

    public static JWebSocketClient client;
    public static JWebSocketClientService.JWebSocketClientBinder binder;
    public static JWebSocketClientService jWebSClientService;

    public static Intent bindIntent;

    @Override
    public void onCreate() {
        super.onCreate();

        new DensityHelper(this, DESIGN_WIDTH).activate();

//        final ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
//                其他配置
//                .cookieJar(cookieJar1)
                .build();

        OkHttpUtils.initClient(okHttpClient);


        MultiDex.install(this);

        // 初始化 TitleBar
        TitleBar.setDefaultInitializer(new LightBarInitializer() {
            @Override
            protected TextView createTextView(Context context) {
                return new AppCompatTextView(context);
            }
        });

        startJWebSClientService();
        bindService();
    }

    public static ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("MainActivity", "服务与活动成功绑定");
            binder = (JWebSocketClientService.JWebSocketClientBinder) iBinder;
            jWebSClientService = binder.getService();
            client = jWebSClientService.client;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("MainActivity", "服务与活动成功断开");
        }
    };

    /**
     * 绑定服务
     */
    private void bindService() {
        bindIntent = new Intent(getApplicationContext(), JWebSocketClientService.class);
        bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    /**
     * 启动服务（websocket客户端服务）
     */
    private void startJWebSClientService() {
        Intent intent = new Intent(getApplicationContext(), JWebSocketClientService.class);
        startService(intent);
    }
}
