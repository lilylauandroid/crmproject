package com.crmproject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.crmproject.util.PermissionUtils;
import com.crmproject.util.SystemBarTintManager;
import com.crmproject.view.BaseLayout;
import com.crmproject.view.ErrorLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity  {


    protected Context mContext;
    protected MyApplication mApp;
    protected BaseLayout baseLayout;
    protected ErrorLayout errorLayout;
    protected ViewGroup immersionLayout;

    public BaseActivity(){
        this.mContext = this;
        mApp = MyApplication.getSelf();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getSelf().push(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mApp!=null){
            mApp.pull(this);
        }
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on,boolean isCloud) {
        //找到最外层的Activity，个别情况是MainTabActivity
        Activity rootActivity = this;
        while (rootActivity.getParent() != null) {
            rootActivity = rootActivity.getParent();
        }
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) rootActivity.findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.setFitsSystemWindows(true);
        viewGroup.setClipToPadding(false);

        //首页跳出
//		if (rootActivity.getClass() == MainSplashActivity.class) {
//
//		} else {
        Window win = rootActivity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        //为状态栏着色
        SystemBarTintManager tintManager = new SystemBarTintManager(rootActivity);
        tintManager.setStatusBarTintEnabled(true);
        if(isCloud){
            //28.4.28暂时注释掉 使用系统默认状态栏 如果使用白色 小米和oppo系统文字颜色会被覆盖
//			tintManager.setStatusBarTintResource(R.color.header_cloud_bg);
        } else {
            tintManager.setStatusBarTintResource(R.color.blue_02);
        }
//		}
    }
    /**
     * 原来未使用setView的布局也添加沉浸式
     *
     * @param layoutResId
     */
    protected void setImmersionView(int layoutResId) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        immersionLayout = (ViewGroup) layoutInflater.inflate(layoutResId, null);
        errorLayout = new ErrorLayout(this,immersionLayout);
        setContentView(immersionLayout);
//        popupWindow = new PopupWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true,true);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }
    /**
     * 启动activity带有动画切换
     *
     * @param intent
     * @param parentActivity
     */
    protected void startActivityForAnima(Intent intent, Activity parentActivity) {
        if (intent != null) {
            if (parentActivity != null) {
                parentActivity.startActivity(intent);
                parentActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
            } else {
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
            }
        }
    }
//    /**
//     * 启动activity带有动画切换
//     *
//     * @param intent
//     * @param requestCode
//     */
//    protected void startActivityForResultAndAnima(Intent intent, int requestCode) {
//        startActivityForResultAndAnima(intent, requestCode, null);
//    }

    /**
     * 启动activity带有动画切换
     *
     * @param intent
     */
    protected void startActivityForAnima(Intent intent) {
        startActivityForAnima(intent, null);
    }
}
