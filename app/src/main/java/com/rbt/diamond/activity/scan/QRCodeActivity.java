package com.rbt.diamond.activity.scan;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.multidex.MultiDex;

import com.rbt.diamond.R;
import com.rbt.diamond.activity.passport.RegisterActivity;

import java.util.List;
import java.util.regex.Pattern;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class QRCodeActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks, QRCodeView.Delegate, View.OnClickListener {
    private TextView start_preview, stop_preview, start_spot, stop_spot,
            start_spot_showrect, stop_spot_hiddenrect, show_scan_rect,
            decode_scan_box_area, decode_full_screen_area, open_flashlight,
            close_flashlight, scan_one_dimension, scan_two_dimension, scan_qr_code,
            scan_code128, scan_ean13, scan_high_frequency, scan_all, scan_custom,
            choose_qrcde_from_gallery;
    private boolean lightIsOpen = false;
    private ImageView flash_light;
    private ConstraintLayout close, light, select_image;
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    private static final String TAG = "zxing";
    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;
    private ZXingView zXingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_code);
        MultiDex.install(this);

        zXingView = findViewById(R.id.zxingview);
        light = findViewById(R.id.light);
        flash_light = findViewById(R.id.flash_light);
        select_image = findViewById(R.id.select_image);
        zXingView.setDelegate(this);

        close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lightIsOpen) {
                    zXingView.closeFlashlight();
                    flash_light.setImageResource(R.mipmap.light_checked);
                    lightIsOpen = false;
                } else {
                    flash_light.setImageResource(R.mipmap.light_nocheck);
                    zXingView.openFlashlight();
                    lightIsOpen = true;
                }
            }
        });

        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent = getGalleryIntent(intent);
                startActivityForResult(intent, REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY);
            }
        });

//        start_preview = findViewById(R.id.start_preview);
//        stop_preview = findViewById(R.id.stop_preview);
//        start_spot = findViewById(R.id.start_spot);
//        stop_spot = findViewById(R.id.stop_spot);
//        start_spot_showrect = findViewById(R.id.start_spot_showrect);
//        stop_spot_hiddenrect = findViewById(R.id.stop_spot_hiddenrect);
//        show_scan_rect = findViewById(R.id.show_scan_rect);
//        decode_scan_box_area = findViewById(R.id.decode_scan_box_area);
//        decode_full_screen_area = findViewById(R.id.decode_full_screen_area);
//        open_flashlight = findViewById(R.id.open_flashlight);
//        close_flashlight = findViewById(R.id.close_flashlight);
//        scan_one_dimension = findViewById(R.id.scan_one_dimension);
//        scan_two_dimension = findViewById(R.id.scan_two_dimension);
//        scan_qr_code = findViewById(R.id.scan_qr_code);
//        scan_code128 = findViewById(R.id.scan_code128);
//        scan_ean13 = findViewById(R.id.scan_ean13);
//        scan_high_frequency = findViewById(R.id.scan_high_frequency);
//        scan_all = findViewById(R.id.scan_all);
//        scan_custom = findViewById(R.id.scan_custom);
//        choose_qrcde_from_gallery = findViewById(R.id.choose_qrcde_from_gallery);
//        stop_preview.setOnClickListener(this);
//        start_spot.setOnClickListener(this);
//        stop_spot.setOnClickListener(this);
//        start_spot_showrect.setOnClickListener(this);
//        stop_spot_hiddenrect.setOnClickListener(this);
//        show_scan_rect.setOnClickListener(this);
//        decode_scan_box_area.setOnClickListener(this);
//        decode_full_screen_area.setOnClickListener(this);
//        open_flashlight.setOnClickListener(this);
//        close_flashlight.setOnClickListener(this);
//        scan_one_dimension.setOnClickListener(this);
//        scan_two_dimension.setOnClickListener(this);
//        scan_qr_code.setOnClickListener(this);
//        scan_code128.setOnClickListener(this);
//        scan_ean13.setOnClickListener(this);
//        scan_high_frequency.setOnClickListener(this);
//        scan_all.setOnClickListener(this);
//        scan_custom.setOnClickListener(this);
//        start_preview.setOnClickListener(this);
//        choose_qrcde_from_gallery.setOnClickListener(this);
    }

    /**
     * 跳转到系统相册：适配19前后的系统
     */
    public static Intent getGalleryIntent(Intent intent) {
        /**19之后的系统相册的图片都存在于MediaStore数据库中；19之前的系统相册中可能包含不存在与数据库中的图片，所以如果是19之上的系统
         * 跳转到19之前的系统相册选择了一张不存在与数据库中的图片，解析uri时就可能会出现null*/
        if (Build.VERSION.SDK_INT < 19) {
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        }
        return intent;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        zXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
            if (data != null) {
                Uri uri = data.getData();
                ContentResolver cr = getContentResolver();
                try {
                    Bitmap mBitmap = MediaStore.Images.Media.getBitmap(cr, uri);//显得到bitmap图片
                    zXingView.decodeQRCode(mBitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if(requestCode == 10000){
            onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        zXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
        //zXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别

        zXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }else{
            zXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
            //zXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别

            zXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
        }
    }

    @Override
    protected void onStop() {
        zXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        zXingView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);

        System.out.println(result);

        System.out.println(result);
        Intent registerIntent = new Intent(QRCodeActivity.this, RegisterActivity.class);
        registerIntent.putExtra("invite_code", result);
        startActivity(registerIntent);
    }

    private static final String[] come = {
            "top", "com.cn", "com", "net", "cn", "cc", "gov", "cn", "hk"
    };

    public static boolean isWebUrl(String url) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (String anExt : come) {
            sb.append(anExt);
            sb.append("|");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        String pattern = "((https?|s?ftp|irc[6s]?|git|afp|telnet|smb|http?)://)?((\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})|((www\\.|[a-zA-Z\\.\\-]+\\.)?[a-zA-Z0-9\\-]+\\." + sb.toString() + "(:[0-9]{1,5})?))((/[a-zA-Z0-9\\./,;\\?'\\+&%\\$#=~_\\-]*)|([^\\u4e00-\\u9fa5\\s0-9a-zA-Z\\./,;\\?'\\+&%\\$#=~_\\-]*))";
        return Pattern.compile(pattern).matcher(url).matches();
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        String tipText = zXingView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                zXingView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                zXingView.getScanBoxView().setTipText(tipText);
            }
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }


    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.start_preview:
//                zXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//                break;
//            case R.id.stop_preview:
//                zXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
//                break;
//            case R.id.start_spot:
//                zXingView.startSpot(); // 开始识别
//                break;
//            case R.id.stop_spot:
//                zXingView.stopSpot(); // 停止识别
//                break;
//            case R.id.start_spot_showrect:
//                zXingView.startSpotAndShowRect(); // 显示扫描框，并且开始识别
//                break;
//            case R.id.stop_spot_hiddenrect:
//                zXingView.stopSpotAndHiddenRect(); // 停止识别，并且隐藏扫描框
//                break;
//            case R.id.show_scan_rect:
//                zXingView.showScanRect(); // 显示扫描框
//                break;
//            case R.id.hidden_scan_rect:
//                zXingView.hiddenScanRect(); // 隐藏扫描框
//                break;
//            case R.id.decode_scan_box_area:
//                zXingView.getScanBoxView().setOnlyDecodeScanBoxArea(true); // 仅识别扫描框中的码
//                break;
//            case R.id.decode_full_screen_area:
//                zXingView.getScanBoxView().setOnlyDecodeScanBoxArea(false); // 识别整个屏幕中的码
//                break;
//            case R.id.open_flashlight:
//                zXingView.openFlashlight(); // 打开闪光灯
//                break;
//            case R.id.close_flashlight:
//                zXingView.closeFlashlight(); // 关闭闪光灯
//                break;
//            case R.id.scan_one_dimension:
//                zXingView.changeToScanBarcodeStyle(); // 切换成扫描条码样式
//                zXingView.setType(BarcodeType.ONE_DIMENSION, null); // 只识别一维条码
//                zXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
//                break;
//            case R.id.scan_two_dimension:
//                zXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
//                zXingView.setType(BarcodeType.TWO_DIMENSION, null); // 只识别二维条码
//                zXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
//                break;
//            case R.id.scan_qr_code:
//                zXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
//                zXingView.setType(BarcodeType.ONLY_QR_CODE, null); // 只识别 QR_CODE
//                zXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
//                break;
//            case R.id.scan_code128:
//                zXingView.changeToScanBarcodeStyle(); // 切换成扫描条码样式
//                zXingView.setType(BarcodeType.ONLY_CODE_128, null); // 只识别 CODE_128
//                zXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
//                break;
//            case R.id.scan_ean13:
//                zXingView.changeToScanBarcodeStyle(); // 切换成扫描条码样式
//                zXingView.setType(BarcodeType.ONLY_EAN_13, null); // 只识别 EAN_13
//                zXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
//                break;
//            case R.id.scan_high_frequency:
//                zXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
//                zXingView.setType(BarcodeType.HIGH_FREQUENCY, null); // 只识别高频率格式，包括 QR_CODE、UPC_A、EAN_13、CODE_128
//                zXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
//                break;
//            case R.id.scan_all:
//                zXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
//                zXingView.setType(BarcodeType.ALL, null); // 识别所有类型的码
//                zXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
//                break;
//            case R.id.scan_custom:
//                zXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
//
//                Map<DecodeHintType, Object> hintMap = new EnumMap<>(DecodeHintType.class);
//                List<BarcodeFormat> formatList = new ArrayList<>();
//                formatList.add(BarcodeFormat.QR_CODE);
//                formatList.add(BarcodeFormat.UPC_A);
//                formatList.add(BarcodeFormat.EAN_13);
//                formatList.add(BarcodeFormat.CODE_128);
//                hintMap.put(DecodeHintType.POSSIBLE_FORMATS, formatList); // 可能的编码格式
//                hintMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE); // 花更多的时间用于寻找图上的编码，优化准确性，但不优化速度
//                hintMap.put(DecodeHintType.CHARACTER_SET, "utf-8"); // 编码字符集
//                zXingView.setType(BarcodeType.CUSTOM, hintMap); // 自定义识别的类型
//
//                zXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
//                break;
//            case R.id.choose_qrcde_from_gallery:
//                /*
//                从相册选取二维码图片，这里为了方便演示，使用的是
//                https://github.com/bingoogolapple/BGAPhotoPicker-Android
//                这个库来从图库中选择二维码图片，这个库不是必须的，你也可以通过自己的方式从图库中选择图片
//                 */
//                Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
//                        .cameraFileDir(null)
//                        .maxChooseCount(1)
//                        .selectedPhotos(null)
//                        .pauseOnScroll(false)
//                        .build();
//                startActivityForResult(photoPickerIntent, REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY);
//                break;
        }
    }

    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];

                }

            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);

            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);

            }

        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);

        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();

        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);

            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        return null;

    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());

    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());

    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());

    }
}