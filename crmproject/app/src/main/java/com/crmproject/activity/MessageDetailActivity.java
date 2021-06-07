package com.crmproject.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.crmproject.BaseActivity;
import com.crmproject.R;
import com.crmproject.util.Utils;


/**
 * @author liuyanli
 * @Creater by user on 2021/6/3
 * @function： 消息明细
 */
public class MessageDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back_img;
    private TextView sign_tv;
    private TextView readed_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        init();
        onClickerListener();
    }

    /**
     * 初始化
     */
    private void init() {

        back_img = findViewById(R.id.message_detail_back_img);
        sign_tv = findViewById(R.id.message_detail_sign_tv);
        readed_tv = findViewById(R.id.message_detail_readed_tv);
    }

    /**
     * 监听
     */
    private void onClickerListener() {

        back_img.setOnClickListener(this);
        sign_tv.setOnClickListener(this);
        readed_tv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_detail_back_img: // 返回

                finish();
                break;
            case R.id.message_detail_sign_tv: // 标记

                Utils.toast(MessageDetailActivity.this,"正在建设中^_^");
                break;
            case R.id.message_detail_readed_tv: // 已阅

                Utils.toast(MessageDetailActivity.this,"正在建设中^_^");
                break;
            default:
                break;

        }

    }
}
