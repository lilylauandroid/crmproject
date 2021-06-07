package com.crmproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


import com.crmproject.BaseActivity;
import com.crmproject.R;
import com.crmproject.util.Utils;

import androidx.appcompat.app.AppCompatActivity;

public class MineActivity extends BaseActivity  implements View.OnClickListener {
    LinearLayout ll_exit,ll_user,ll_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        ll_exit = findViewById(R.id.ll_exit);
        ll_user = findViewById(R.id.ll_user);
        ll_message = findViewById(R.id.ll_message);
        ll_exit.setOnClickListener(this);
        ll_user.setOnClickListener(this);
        ll_message.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.ll_exit:
                Intent intent = new Intent(mContext,LoginActivity.class);
                startActivityForAnima(intent);
                Activity parent = this.getParent();
                if(parent!=null){
                    parent.finish();
                }
                break;
            default:
                Utils.toast(mContext, "正在建设中^_^");
                break;
        }
    }
}