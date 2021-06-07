package com.crmproject.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.crmproject.BaseActivity;
import com.crmproject.R;


/**
 * @author liuyanli
 * @Creater by user on 2021/6/4
 * @function： 月消耗
 */
public class MonthConsumeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_consume);

        init();
        onClickerListener();
    }

    /**
     * 初始化
     */
    private void init() {

        back_img = findViewById(R.id.month_consume_back_img);

    }

    /**
     * 监听
     */
    private void onClickerListener() {

        back_img.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.month_consume_back_img: // 返回

                finish();
                break;
            default:
                break;

        }

    }
}
