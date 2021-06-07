package com.crmproject.activity;

import android.app.Dialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.crmproject.R;

import java.util.Observable;
import java.util.Observer;


//import com.soufun.agent.service.ChatService;


public class MainTabActivity extends TabActivity implements CompoundButton.OnCheckedChangeListener {
    public static final int C_GET_NEW_MESSAGE = 10;// 获取
    // 未读消息的条数成功
    public static final int C_GET_NEW_MESSAGE_FAIL = C_GET_NEW_MESSAGE + 1;// 获取
    private TabHost host;
    private RadioButton[] radioButtons;
    private Context mContext;
    private boolean firstCreate;
    private int oldSwitchId = -1;
    private Animation animationRightIn, animationRightOut, animationLeftIn,
            animationLeftOut;
    public static final String TAB_HOME = "tab_home";
    public static final String TAB_MESSAGE = "tab_message";
    public static final String TAB_DYNAMIC = "tab_dynamic";
    public static final String TAB_INFO = "tab_info";
    /**客户*/
    public static final String TAB_CUSTOMER = "tab_customer";
    /**逛逛*/
    public static final String TAB_STROLL_AROUND = "tab_stroll_around";
    /**直播获客*/
    public static final String TAB_LIVE = "tab_live";
    public static String from;
    private TextView tv_message_count;
    private TextView tv_message_count_personal;
    RadioGroup radioGroup;

    public static MainTabActivity mMainTabActivity;
    private final int TEMPJUMP = 1000001;
    private RefreshMsgObserver mRefreshMsgObserver;
    /**人脸识别dialog*/
    private Dialog faceDialog;
    /**是否是走进onCreate方法*/
    private boolean isOnCreate;
    /**tab上边分割线*/
    private View vTabDivider;
    /**选中背景集合*/
    Drawable[] selectedDrawables;
    /**未选中背景集合*/
    Drawable[] unSelectedDrawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainTabActivity = this;
        mContext = this;
        firstCreate = true;
        setContentView(R.layout.main_tab);
        host = getTabHost();

        initViews();
        initData();
        initHost();
        initChecked();



        isOnCreate = true;

    }

    public void initHost(){
        host.clearAllTabs();

        host.addTab(host.newTabSpec(TAB_HOME).setIndicator(TAB_HOME).setContent(new Intent(this, HomeActivity.class)));
        host.addTab(host.newTabSpec(TAB_MESSAGE).setIndicator(TAB_MESSAGE).setContent(new Intent(this, LookActivity.class)));
        host.addTab(host.newTabSpec(TAB_DYNAMIC).setIndicator(TAB_DYNAMIC).setContent(new Intent(this, MenuActivity.class)));
        host.addTab(host.newTabSpec(TAB_INFO).setIndicator(TAB_INFO).setContent(new Intent(this, MineActivity.class)));
        radioButtons[0].setChecked(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }




    private void initViews() {
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        vTabDivider = findViewById(R.id.view_tab_divider);
        selectedDrawables = new Drawable[4];
        unSelectedDrawables = new Drawable[4];

    }

    private void initData() {
        animationRightIn = AnimationUtils.loadAnimation(this,
                R.anim.push_right_in);
        animationRightOut = AnimationUtils.loadAnimation(this,
                R.anim.push_right_out);
        animationLeftIn = AnimationUtils.loadAnimation(this,
                R.anim.push_left_in);
        animationLeftOut = AnimationUtils.loadAnimation(this,
                R.anim.push_left_out);

        vTabDivider.setVisibility(View.VISIBLE);
        radioButtons = new RadioButton[4];
        for (int i = 0; i < 4; i++) {
            radioButtons[i] = (RadioButton) radioGroup.findViewWithTag("radio_button" + i);
            radioButtons[i].setOnCheckedChangeListener(this);
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void initChecked() {
        int switchid = 0;
        host.setCurrentTab(switchid);
        radioButtons[switchid].setChecked(true);
    }

    public void switchTab(int index){
        radioButtons[index].setChecked(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String flag = intent.getStringExtra("flag");
        fetchPush(intent);
        setIntent(intent);
        if(!"close".equals(flag)){
            initHost();
        }
        initChecked();
    }

    private void fetchPush(Intent intent){

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SEARCH) {
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (event.getRepeatCount() == 0) {
                if (oldSwitchId == 0) {
                    exit();
                } else {
                    radioButtons[0].setChecked(true);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        // 退出到后台
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            for (int i = 0; i < radioButtons.length; i++) {
                if (buttonView == radioButtons[i]) {


                    if (firstCreate) {
                        host.setCurrentTab(i);
                    } else {
                        if (oldSwitchId > i) {
                            host.getCurrentView().startAnimation(
                                    animationRightOut);
                            host.setCurrentTab(i);
                            host.getCurrentView().startAnimation(
                                    animationRightIn);
                        } else if (oldSwitchId < i) {
                            host.getCurrentView().startAnimation(
                                    animationLeftOut);
                            host.setCurrentTab(i);
                            host.getCurrentView().startAnimation(
                                    animationLeftIn);
                        }
                    }
                    firstCreate = false;
                    oldSwitchId = i;

                }
            }
        }
    }

    private class RefreshMsgObserver implements Observer {
        @Override
        public void update(Observable observable, Object data) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == TEMPJUMP && intent != null){
            fetchPush(intent);
            setIntent(intent);
            initChecked();
        }
    }





}
