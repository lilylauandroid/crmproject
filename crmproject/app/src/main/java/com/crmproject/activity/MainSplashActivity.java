package com.crmproject.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;


import com.crmproject.BaseActivity;
import com.crmproject.R;
import com.crmproject.util.DangerousPermissions;
import com.crmproject.util.PermissionUtils;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;


public class MainSplashActivity extends BaseActivity {

    private static final String[] LOCATION_STATE_STORAGE =
            { DangerousPermissions.READ_PHONE_STATE, DangerousPermissions.READ_EXTERNAL_STORAGE,DangerousPermissions.WRITE_EXTERNAL_STORAGE};


    private Intent intent;
    private AlphaAnimation animation;
    ImageView tv_hello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmersionView(R.layout.activity_main_splash);
        tv_hello = findViewById(R.id.tv_hello);
//        tv_hello.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent();
//                    intent.setClass(mContext, LoginActivity.class);
//                    startActivityForAnima(intent);
//                    finish();
//            }
//        },3000);
        // 透明动画（从完全透明到不透明，分别对应第一个参数和第二个参数）
        animation = new AlphaAnimation(0.1f, 1.0f);
        // 动画效果时间为3秒
        animation.setDuration(3000);
        // 动画监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { // 动画开始时执行此方法
            }

            @Override
            public void onAnimationRepeat(Animation animation) { // 动画重复调用时执行此方法
            }

            @Override
            public void onAnimationEnd(Animation animation) { // 动画结束时执行此方法
                 Intent intent = new Intent();
                    intent.setClass(mContext, LoginActivity.class);
                    startActivityForAnima(intent);
                    finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        requestPermisssions();
    }

    /**
     * 处理权限的方法
     */
    public void requestPermisssions(){

        if (!PermissionUtils.checkPermissions(MainSplashActivity.this,LOCATION_STATE_STORAGE)){
            ActivityCompat.requestPermissions(MainSplashActivity.this,LOCATION_STATE_STORAGE, PermissionUtils.REQUEST_SPLASH_PERMISSIONS);
        }else {
            tv_hello.startAnimation(animation);
        }
    }



    /**
     * 这里需要处理授权的结果， 复写onRequestPermissionsResult方法
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean isAllGranted = true;//是否全部权限授权
        for (int grantResult : grantResults){
            if (grantResult == PackageManager.PERMISSION_DENIED){
                isAllGranted = false;
                break;
            }
        }
        if (isAllGranted){//已全部授权
            finish();
            startActivityForAnima(new Intent(MainSplashActivity.this, LoginActivity.class));
        }else {//有未授权的权限
            PermissionUtils.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
        }
    }
}
