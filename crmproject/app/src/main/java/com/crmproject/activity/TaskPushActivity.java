package com.crmproject.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crmproject.BaseActivity;
import com.crmproject.R;


/**
 * @author liuyanli
 * @Creater by user on 2021/6/4
 * @function： 任务推动
 */
public class TaskPushActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back_img;

    private ImageView agree_img; // 批准
    private LinearLayout agree_ll;
    private ImageView disagree_img; // 驳回
    private LinearLayout disagree_ll;

    private EditText opinion_ed; // 审核意见
    private TextView cancel_tv; // 取消
    private TextView commit_tv; // 提交

    private int opininId = -1; // 0批准 1驳回


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_push);

        init();
        onClickerListener();
    }

    /**
     * 初始化
     */
    private void init() {

        back_img = findViewById(R.id.task_push_back_img);

        agree_img = findViewById(R.id.task_push_agree_img);
        agree_ll = findViewById(R.id.task_push_agree_ll);
        disagree_img = findViewById(R.id.task_push_disagree_img);
        disagree_ll = findViewById(R.id.task_push_disagree_ll);
        opinion_ed = findViewById(R.id.task_push_opinion_ed);
        cancel_tv = findViewById(R.id.task_push_bottom_cancel_tv);
        commit_tv = findViewById(R.id.task_push_bottom_commit_tv);

    }

    /**
     * 监听
     */
    private void onClickerListener() {

        back_img.setOnClickListener(this);
        agree_ll.setOnClickListener(this);
        disagree_ll.setOnClickListener(this);
        cancel_tv.setOnClickListener(this);
        commit_tv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.task_push_back_img: // 返回

                finish();
                break;
            case R.id.task_push_agree_ll: // 批准

               if (opininId == 0) { // 取消选择

                    opininId = -1;
                    agree_img.setImageResource(R.drawable.radio_unselected_icon);
                    disagree_img.setImageResource(R.drawable.radio_unselected_icon);
                } else {
                    opininId = 0;
                    agree_img.setImageResource(R.drawable.radio_selected_icon);
                    disagree_img.setImageResource(R.drawable.radio_unselected_icon);
                }
                break;
            case R.id.task_push_disagree_ll: // 驳回

                if (opininId == 1) { // 取消选择

                    opininId = -1;
                    agree_img.setImageResource(R.drawable.radio_unselected_icon);
                    disagree_img.setImageResource(R.drawable.radio_unselected_icon);
                } else {
                    opininId = 1;
                    agree_img.setImageResource(R.drawable.radio_unselected_icon);
                    disagree_img.setImageResource(R.drawable.radio_selected_icon);
                }
                break;
            case R.id.task_push_bottom_cancel_tv: // 取消

                finish();
                break;
            case R.id.task_push_bottom_commit_tv: // 提交
                finish();
                break;
            default:
                break;

        }

    }
}
