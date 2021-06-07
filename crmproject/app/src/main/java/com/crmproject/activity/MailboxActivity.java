package com.crmproject.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.crmproject.BaseActivity;
import com.crmproject.R;

public class MailboxActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back_img; // 返回
    private LinearLayout list_ll1;
    private LinearLayout list_ll2;
    private LinearLayout list_ll3;
    private LinearLayout list_ll4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailbox);


        init();
        onClickListener();
    }

    // 初始化
    private void init() {

        back_img = findViewById(R.id.mailbox_img);
        list_ll1 = findViewById(R.id.mailbox_ll1);
        list_ll2 = findViewById(R.id.mailbox_ll2);
        list_ll3 = findViewById(R.id.mailbox_ll3);
        list_ll4 = findViewById(R.id.mailbox_ll4);
    }

    // 监听
    private void onClickListener() {
        back_img.setOnClickListener(this);
        list_ll1.setOnClickListener(this);
        list_ll2.setOnClickListener(this);
        list_ll3.setOnClickListener(this);
        list_ll4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.mailbox_img: // 返回
                finish();
                break;
            case R.id.mailbox_ll1: // 跳转工作流任务
                intent = new Intent(MailboxActivity.this, WorkFlowActivity.class);
                startActivity(intent);
                break;
            case R.id.mailbox_ll2: // 跳转工作流任务
                intent = new Intent(MailboxActivity.this, WorkFlowActivity.class);
                startActivity(intent);
                break;
            case R.id.mailbox_ll3: // 跳转工作流任务
                intent = new Intent(MailboxActivity.this, WorkFlowActivity.class);
                startActivity(intent);
                break;
            case R.id.mailbox_ll4: // 跳转工作流任务
                intent = new Intent(MailboxActivity.this, WorkFlowActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
