package com.crmproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crmproject.BaseActivity;
import com.crmproject.R;
import com.crmproject.util.StringUtils;
import com.crmproject.util.Utils;


public class LoginActivity extends BaseActivity {

    Button tvLogin;
    //账号，密码，手机号，验证码
    private EditText et_login_password, et_login_username;
    //密码的隐藏按钮
    private ImageView iv_login_password;
    //手机验证码登录,新用户注册,忘记密码
    private TextView tv_login_phone, tv_login_forget_password;
    private RelativeLayout rl_login_password_hide;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        setImmersionView(R.layout.activity_login);
        //账户密码登录
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        et_login_username = (EditText) findViewById(R.id.et_login_username);
        iv_login_password = (ImageView) findViewById(R.id.iv_login_password);
        rl_login_password_hide = (RelativeLayout) findViewById(R.id.rl_login_password_hide);
        tvLogin = findViewById(R.id.btn_login);
        tvLogin.setOnClickListener(onClickListener);
        rl_login_password_hide.setOnClickListener(onClickListener);
        et_login_username.setSelection(et_login_username.getText().length());
    }
    /**
     * 点击事件
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login:
                    if (StringUtils.isNullOrEmpty(et_login_username.getText().toString())) {
                        Utils.toast(LoginActivity.this, "请输入用户名/密码");
                        break;
                    }
                    if (StringUtils.isNullOrEmpty(et_login_password.getText()
                            .toString())) {
                        Utils.toast(LoginActivity.this, "请输入密码");
                        break;
                    }
//                    new CloudLoginTask().execute();
                    intent = new Intent();
                    intent.setClass(LoginActivity.this, MainTabActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                //手机验证码登录跟账号密码登录切换
                case R.id.tv_login_phone:
                    tv_login_phone.setVisibility(View.GONE);
//                    tv_login_password.setVisibility(View.VISIBLE);
//                    ll_login_username.setVisibility(View.GONE);
//                    ll_login_phone.setVisibility(View.VISIBLE);
//                    rl_login_code_bottom.setVisibility(View.VISIBLE);
                    tv_login_forget_password.setText("收不到验证码");

                    break;
                case R.id.tv_login_password:
                    tv_login_phone.setVisibility(View.VISIBLE);
//                    tv_login_password.setVisibility(View.GONE);
//                    ll_login_username.setVisibility(View.VISIBLE);
//                    ll_login_phone.setVisibility(View.GONE);
//                    rl_login_code_bottom.setVisibility(View.GONE);
                    tv_login_forget_password.setText("重置密码");

                    break;
                case R.id.rl_login_clear_username:
                    et_login_username.setText("");
                    break;
                //忘记密码
//                case R.id.tv_login_forget_password:
//                    if("收不到验证码".equals(tv_login_forget_password.getText().toString())){
//                        startActivity(new Intent(mContext,BaseWebActivity.class).putExtra("url",URL_NO_SMS_CODE));
//                    }else{
//                        startActivity(new Intent(LoginNewActivity.this, ResetPasswordNewActivity.class).putExtra("isLogin",true));
//                    }
//                    break;

                //隐藏密码
                case R.id.rl_login_password_hide:
                    Utils.hideSoftKeyBoard(LoginActivity.this);
                    ConfirmPasswordShowOnClick();
                    break;
                case R.id.tv_feedback: // 帮助与反馈
                    intent = new Intent();
                    intent.setClass(LoginActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;

                //注册


            }
        }
    };
    /**
     * 判断  确认密码  处眼睛是否是可见状态  true可见
     */
    private boolean isConfirmPassWordShow = false;

    private void ConfirmPasswordShowOnClick() {
        if (!isConfirmPassWordShow) {
            //设置EditText文本为可见的
            et_login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            iv_login_password.setImageResource(R.drawable.login_password_up);
        } else {
            //设置EditText文本为隐藏的
            et_login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            iv_login_password.setImageResource(R.drawable.login_password_down);
        }
        isConfirmPassWordShow = !isConfirmPassWordShow;
        et_login_password.postInvalidate();
        //切换后将EditText光标置于末尾
        CharSequence charSequence = et_login_password.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }
}