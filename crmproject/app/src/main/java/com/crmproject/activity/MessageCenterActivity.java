package com.crmproject.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crmproject.BaseActivity;
import com.crmproject.R;
import com.crmproject.util.Utils;


/**
 * @author liuyanli
 * @Creater by user on 2021/6/3
 * @function： 消息中心
 */
public class MessageCenterActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back_img; // 返回
    private ImageView select_img; // 筛选
    private View selectbg_view; // 半透明背景
    private LinearLayout selectPop_ll; // 筛选弹出
    private TextView select_all_tv; // 全部
    private TextView select_day_tv; // 当日
    private TextView select_week_tv; // 本周
    private TextView select_month_tv; // 本月
    private boolean popIsShow = false; // true弹出筛选 false关闭
    private int selectDateId = 0; // 时间id 0全部 1当日 2本周 3本月

    private LinearLayout tab_all_ll; // 全部
    private TextView tab_all_tv;
    private LinearLayout tab_product_ll; // 生产
    private TextView tab_product_tv; //
    private LinearLayout tab_electronic_ll; // 机电
    private TextView tab_electronic_tv;
    private LinearLayout tab_security_ll; // 安全
    private TextView tab_security_tv;
    private LinearLayout tab_integrate_ll; // 综合
    private TextView tab_integrate_tv;
    private int typeId = 0; // 业务id 0全部 1生产 2机电 3安全 4综合

    private TextView history_tv; // 历史消息
    private TextView unread_tv; // 待处理

    private LinearLayout list_ll1;
    private LinearLayout list_ll2;
    private LinearLayout list_ll3;
    private LinearLayout list_ll4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);


        init();
        onClickListener();
    }

    // 初始化
    private void init() {

        back_img = findViewById(R.id.message_center_back_img);
        select_img = findViewById(R.id.message_center_select_img);

        tab_all_ll = findViewById(R.id.message_center_tab_all_ll);
        tab_all_tv = findViewById(R.id.message_center_tab_all_tv);
        tab_all_tv.setBackground(getResources().getDrawable(R.drawable.bgf5f5f5_radius4));
        tab_product_ll = findViewById(R.id.message_center_tab_product_ll);
        tab_product_tv = findViewById(R.id.message_center_tab_product_tv);
        tab_electronic_ll = findViewById(R.id.message_center_tab_electronic_ll);
        tab_electronic_tv = findViewById(R.id.message_center_tab_electronic_tv);
        tab_security_ll = findViewById(R.id.message_center_tab_security_ll);
        tab_security_tv = findViewById(R.id.message_center_tab_security_tv);
        tab_integrate_ll = findViewById(R.id.message_center_tab_integrate_ll);
        tab_integrate_tv = findViewById(R.id.message_center_tab_integrate_tv);

        selectbg_view = findViewById(R.id.message_center_selectbg_view);
        selectPop_ll = findViewById(R.id.message_center_selectpop_ll);
        select_all_tv = findViewById(R.id.message_center_selectpop_all_tv);
        select_day_tv = findViewById(R.id.message_center_selectpop_day_tv);
        select_week_tv = findViewById(R.id.message_center_selectpop_week_tv);
        select_month_tv = findViewById(R.id.message_center_selectpop_month_tv);

        history_tv = findViewById(R.id.message_center_history_tv);
        unread_tv = findViewById(R.id.message_center_unread_tv);

        list_ll1 = findViewById(R.id.message_center_list_ll1);
        list_ll2 = findViewById(R.id.message_center_list_ll2);
        list_ll3 = findViewById(R.id.message_center_list_ll3);
        list_ll4 = findViewById(R.id.message_center_list_ll4);

    }

    // 监听
    private void onClickListener() {
        back_img.setOnClickListener(this);
        select_img.setOnClickListener(this);

        tab_all_ll.setOnClickListener(this);
        tab_product_ll.setOnClickListener(this);
        tab_electronic_ll.setOnClickListener(this);
        tab_security_ll.setOnClickListener(this);
        tab_integrate_ll.setOnClickListener(this);

        selectbg_view.setOnClickListener(this);
        selectPop_ll.setOnClickListener(this);
        select_all_tv.setOnClickListener(this);
        select_day_tv.setOnClickListener(this);
        select_week_tv.setOnClickListener(this);
        select_month_tv.setOnClickListener(this);

        history_tv.setOnClickListener(this);
        unread_tv.setOnClickListener(this);

        list_ll1.setOnClickListener(this);
        list_ll2.setOnClickListener(this);
        list_ll3.setOnClickListener(this);
        list_ll4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.message_center_list_ll1: // 消息明细
                intent = new Intent(MessageCenterActivity.this, MessageDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.message_center_list_ll2: // 消息明细
                intent = new Intent(MessageCenterActivity.this, MessageDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.message_center_list_ll3: // 消息明细
                intent = new Intent(MessageCenterActivity.this, MessageDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.message_center_list_ll4: // 消息明细
                intent = new Intent(MessageCenterActivity.this, MessageDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.message_center_back_img: // 返回
                finish();
                break;
            case R.id.message_center_select_img: // 筛选

                if (popIsShow) { // 关闭
                    popIsShow = false;
                    selectbg_view.setVisibility(View.GONE);
                    selectPop_ll.setVisibility(View.GONE);
                } else { // 弹出
                    popIsShow = true;
                    selectbg_view.setVisibility(View.VISIBLE);
                    selectPop_ll.setVisibility(View.VISIBLE);

                    if (selectDateId == 0) {
                        select_all_tv.setTextColor(Color.parseColor("#BE1E13"));
                        select_day_tv.setTextColor(Color.parseColor("#ff0e131a"));
                        select_week_tv.setTextColor(Color.parseColor("#ff0e131a"));
                        select_month_tv.setTextColor(Color.parseColor("#ff0e131a"));
                    } else if (selectDateId == 1) {
                        select_all_tv.setTextColor(Color.parseColor("#ff0e131a"));
                        select_day_tv.setTextColor(Color.parseColor("#BE1E13"));
                        select_week_tv.setTextColor(Color.parseColor("#ff0e131a"));
                        select_month_tv.setTextColor(Color.parseColor("#ff0e131a"));
                    } else if (selectDateId == 2) {
                        select_all_tv.setTextColor(Color.parseColor("#ff0e131a"));
                        select_day_tv.setTextColor(Color.parseColor("#ff0e131a"));
                        select_week_tv.setTextColor(Color.parseColor("#BE1E13"));
                        select_month_tv.setTextColor(Color.parseColor("#ff0e131a"));
                    } else if (selectDateId == 3) {
                        select_all_tv.setTextColor(Color.parseColor("#ff0e131a"));
                        select_day_tv.setTextColor(Color.parseColor("#ff0e131a"));
                        select_week_tv.setTextColor(Color.parseColor("#ff0e131a"));
                        select_month_tv.setTextColor(Color.parseColor("#BE1E13"));
                    }
                }
                break;
            case R.id.message_center_selectbg_view: // 半透明背景

                popIsShow = false;
                selectbg_view.setVisibility(View.GONE);
                selectPop_ll.setVisibility(View.GONE);
                break;
            case R.id.message_center_selectpop_all_tv: //时间 全部

                selectDateId = 0;
                popIsShow = false;
                selectbg_view.setVisibility(View.GONE);
                selectPop_ll.setVisibility(View.GONE);
                break;

            case R.id.message_center_selectpop_day_tv: // 当日

                selectDateId = 1;
                popIsShow = false;
                selectbg_view.setVisibility(View.GONE);
                selectPop_ll.setVisibility(View.GONE);
                break;

            case R.id.message_center_selectpop_week_tv: // 本周

                selectDateId = 2;
                popIsShow = false;
                selectbg_view.setVisibility(View.GONE);
                selectPop_ll.setVisibility(View.GONE);
                break;

            case R.id.message_center_selectpop_month_tv: // 本月

                selectDateId = 3;
                popIsShow = false;
                selectbg_view.setVisibility(View.GONE);
                selectPop_ll.setVisibility(View.GONE);
                break;

            case R.id.message_center_tab_all_ll: // 业务 全部

                typeId = 0;

                tab_all_tv.setTextColor(Color.parseColor("#ffbe1e13"));
                tab_product_tv.setTextColor(Color.parseColor("#666666"));
                tab_electronic_tv.setTextColor(Color.parseColor("#666666"));
                tab_security_tv.setTextColor(Color.parseColor("#666666"));
                tab_integrate_tv.setTextColor(Color.parseColor("#666666"));

                tab_all_tv.setBackground(getResources().getDrawable(R.drawable.bgf5f5f5_radius4));
                tab_product_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_electronic_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_security_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_integrate_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));

                break;
            case R.id.message_center_tab_product_ll: // 生产

                typeId = 1;

                tab_all_tv.setTextColor(Color.parseColor("#666666"));
                tab_product_tv.setTextColor(Color.parseColor("#ffbe1e13"));
                tab_electronic_tv.setTextColor(Color.parseColor("#666666"));
                tab_security_tv.setTextColor(Color.parseColor("#666666"));
                tab_integrate_tv.setTextColor(Color.parseColor("#666666"));

                tab_all_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_product_tv.setBackground(getResources().getDrawable(R.drawable.bgf5f5f5_radius4));
                tab_electronic_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_security_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_integrate_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));

                break;
            case R.id.message_center_tab_electronic_ll: // 机电

                typeId = 2;

                tab_all_tv.setTextColor(Color.parseColor("#666666"));
                tab_product_tv.setTextColor(Color.parseColor("#666666"));
                tab_electronic_tv.setTextColor(Color.parseColor("#ffbe1e13"));
                tab_security_tv.setTextColor(Color.parseColor("#666666"));
                tab_integrate_tv.setTextColor(Color.parseColor("#666666"));

                tab_all_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_product_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_electronic_tv.setBackground(getResources().getDrawable(R.drawable.bgf5f5f5_radius4));
                tab_security_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_integrate_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));

                break;
            case R.id.message_center_tab_security_ll: // 安全

                typeId = 3;

                tab_all_tv.setTextColor(Color.parseColor("#666666"));
                tab_product_tv.setTextColor(Color.parseColor("#666666"));
                tab_electronic_tv.setTextColor(Color.parseColor("#666666"));
                tab_security_tv.setTextColor(Color.parseColor("#ffbe1e13"));
                tab_integrate_tv.setTextColor(Color.parseColor("#666666"));

                tab_all_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_product_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_electronic_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_security_tv.setBackground(getResources().getDrawable(R.drawable.bgf5f5f5_radius4));
                tab_integrate_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));

                break;
            case R.id.message_center_tab_integrate_ll: // 综合

                typeId = 4;

                tab_all_tv.setTextColor(Color.parseColor("#666666"));
                tab_product_tv.setTextColor(Color.parseColor("#666666"));
                tab_electronic_tv.setTextColor(Color.parseColor("#666666"));
                tab_security_tv.setTextColor(Color.parseColor("#666666"));
                tab_integrate_tv.setTextColor(Color.parseColor("#ffbe1e13"));

                tab_all_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_product_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_electronic_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_security_tv.setBackground(getResources().getDrawable(R.drawable.bgffffff_radius6));
                tab_integrate_tv.setBackground(getResources().getDrawable(R.drawable.bgf5f5f5_radius4));

                break;
            case R.id.message_center_history_tv:// 历史消息

                Utils.toast(MessageCenterActivity.this,"正在建设中^_^");
                break;
            case R.id.message_center_unread_tv: // 待处理

                Utils.toast(MessageCenterActivity.this,"正在建设中^_^");
                break;

            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        popIsShow = false;
        selectbg_view.setVisibility(View.GONE);
        selectPop_ll.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        popIsShow = false;
        selectbg_view.setVisibility(View.GONE);
        selectPop_ll.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        popIsShow = false;
        selectbg_view.setVisibility(View.GONE);
        selectPop_ll.setVisibility(View.GONE);
    }
}
