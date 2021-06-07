package com.crmproject.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.crmproject.BaseActivity;
import com.crmproject.R;
import com.crmproject.util.Utils;


/**
 * @author liuyanli
 * @Creater by user on 2021/6/4
 * @function： 工作流任务
 */
public class WorkFlowActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back_img;
    private TextView push_tv; // 推动
    private TextView history_tv; // 审核历史
    private TextView relative_tv; // 相关业务
    private TextView sgin_tv; // 标记


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_flow);

        init();
        onClickerListener();
    }

    /**
     * 初始化
     */
    private void init() {

        back_img = findViewById(R.id.work_flow_back_img);
        push_tv = findViewById(R.id.work_flow_push_tv);
        history_tv = findViewById(R.id.work_flow_history_tv);
        relative_tv = findViewById(R.id.work_flow_relative_tv);
        sgin_tv = findViewById(R.id.work_flow_sign_tv);
    }

    /**
     * 监听
     */
    private void onClickerListener() {

        back_img.setOnClickListener(this);
        push_tv.setOnClickListener(this);
        history_tv.setOnClickListener(this);
        relative_tv.setOnClickListener(this);
        sgin_tv.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.work_flow_back_img: // 返回

                finish();
                break;

            case R.id.work_flow_push_tv: // 推动
                Intent intent = new Intent(WorkFlowActivity.this, TaskPushActivity.class);
                startActivity(intent);

                break;
            case R.id.work_flow_history_tv: // 审核历史

                Utils.toast(WorkFlowActivity.this,"正在建设中^_^");
                break;
            case R.id.work_flow_relative_tv: // 相关业务

                Utils.toast(WorkFlowActivity.this,"正在建设中^_^");
                break;
            case R.id.work_flow_sign_tv: // 标记

                Utils.toast(WorkFlowActivity.this,"正在建设中^_^");
                break;
            default:
                break;

        }

    }
}
