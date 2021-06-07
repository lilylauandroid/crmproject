package com.crmproject;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private static MyApplication mApp;
    private List<Activity> mActivities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        mApp = (MyApplication) getApplicationContext();
        toastMgr.builder.init(mApp);
    }

    public void push(Activity activity){
        for (int i=0;i<mActivities.size();i++){
            if(mActivities.get(i)==activity){
                return;
            }
        }
        mActivities.add(activity);
    }

    public void pull(Activity activity){
        for(int i=0;i<mActivities.size();i++){
            if(mActivities.get(i)==activity){
                mActivities.remove(i);
                return;
            }
        }
    }

    public static synchronized MyApplication getSelf(){
        return mApp;
    }

    public void ActivitiesDestroy(){
        for(Activity activity : mActivities){
            activity.finish();
        }
    }
    /**
     * toast singleton，用来统一显示toast，这样就可以实现toast的快速刷新
     */
    public enum toastMgr {
        builder;

        private View v;
        private TextView tv;
        private Toast toast;

        private void init(Context c) {
            v = LayoutInflater.from(c).inflate(R.layout.toast, null);
            tv = (TextView) v.findViewById(R.id.tv_toast);
            toast = new Toast(c);
            toast.setView(v);
        }

        public void display(CharSequence text, int duration) {
            if (text != null && text.length() != 0) {
                tv.setText(text);
                toast.setDuration(duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }

        public void display(int Resid, int duration) {
            if (Resid != 0) {
                tv.setText(Resid);
                toast.setDuration(duration);
                toast.show();
            }
        }
    }

}
