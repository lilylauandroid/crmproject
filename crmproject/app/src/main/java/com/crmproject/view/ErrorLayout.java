package com.crmproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crmproject.R;
import com.crmproject.util.StringUtils;
import com.crmproject.util.Utils;


/**
 * 作者：user on 2017/2/7 17:34
 * 邮箱：xushichao@fang.com
 */

/**
 * 作者：user on 2017/2/7 17:34
 * 邮箱：xushichao@fang.com
 */
public class ErrorLayout extends LinearLayout {

    public View errorView;
    public LinearLayout mNetErrorLl,mDataNullLl,mDataErrorLl,mNullCenterLl,mNullBottomLl;
    public TextView mDataNullTv,mDataNullBtn,mDividingLineTv,mTipsContentTv;
    private boolean errorViewExist;

    /**
     * 此构造器应用于xml文件中
     * @param context
     * @param attrsTextView
     */
    public ErrorLayout(Context context, AttributeSet attrsTextView) {
        super(context, attrsTextView);
        errorViewExist = true;
        LayoutInflater.from(context).inflate(R.layout.eb_sale_error, this, true);
//        addView(errorView);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mNetErrorLl = (LinearLayout) findViewById(R.id.ll_net_error);
        mDataNullLl = (LinearLayout) findViewById(R.id.ll_data_null);
        mDataErrorLl = (LinearLayout) findViewById(R.id.ll_data_error);
        mNullCenterLl = (LinearLayout) findViewById(R.id.ll_data_null_center);
        mNullBottomLl = (LinearLayout) findViewById(R.id.ll_data_null_bottom);
        mDataNullTv = (TextView) findViewById(R.id.tv_data_null);
        mDataNullBtn = (TextView)findViewById(R.id.btn_data_null);
        mDividingLineTv = (TextView) findViewById(R.id.tv_dividing_line);
        mTipsContentTv = (TextView) findViewById(R.id.tv_tips_content);
    }

    /**
     * 自动加载，使用时需将布局最外层设置为RelativeLayout
     * @param context
     * @param baseLayout
     */
    public ErrorLayout(Context context, BaseLayout baseLayout) {
        super(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        ViewGroup viewGroup = (ViewGroup) baseLayout.getChildAt(1);
        if(!(viewGroup instanceof RelativeLayout)){
            return;
        }
        errorViewExist = true;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        errorView = layoutInflater.inflate(R.layout.eb_sale_error, null);
        errorView.setId(R.id.sale_error);
        mNetErrorLl = (LinearLayout) errorView.findViewById(R.id.ll_net_error);
        mDataNullLl = (LinearLayout) errorView.findViewById(R.id.ll_data_null);
        mDataErrorLl = (LinearLayout) errorView.findViewById(R.id.ll_data_error);
        mNullCenterLl = (LinearLayout) errorView.findViewById(R.id.ll_data_null_center);
        mNullBottomLl = (LinearLayout) errorView.findViewById(R.id.ll_data_null_bottom);
        mDataNullTv = (TextView) errorView.findViewById(R.id.tv_data_null);
        mDataNullBtn = (TextView) errorView.findViewById(R.id.btn_data_null);
        mDividingLineTv = (TextView) errorView.findViewById(R.id.tv_dividing_line);
        mTipsContentTv = (TextView) errorView.findViewById(R.id.tv_tips_content);

        viewGroup.addView(errorView, params);
    }

    /**
     * 自动加载，使用时需将布局最外层设置为RelativeLayout
     * @param context
     * @param baseLayout
     */
    public ErrorLayout(Context context, ViewGroup baseLayout) {
        super(context);
        if(!(baseLayout instanceof RelativeLayout)){
            return;
        }
        errorViewExist = true;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        errorView = layoutInflater.inflate(R.layout.eb_sale_error, null);
        errorView.setId(R.id.sale_error);
        mNetErrorLl = (LinearLayout) errorView.findViewById(R.id.ll_net_error);
        mDataNullLl = (LinearLayout) errorView.findViewById(R.id.ll_data_null);
        mDataErrorLl = (LinearLayout) errorView.findViewById(R.id.ll_data_error);
        mNullCenterLl = (LinearLayout) errorView.findViewById(R.id.ll_data_null_center);
        mNullBottomLl = (LinearLayout) errorView.findViewById(R.id.ll_data_null_bottom);
        mDataNullTv = (TextView) errorView.findViewById(R.id.tv_data_null);
        mDataNullBtn = (TextView) errorView.findViewById(R.id.btn_data_null);
        mDividingLineTv = (TextView) errorView.findViewById(R.id.tv_dividing_line);
        mTipsContentTv = (TextView) errorView.findViewById(R.id.tv_tips_content);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        baseLayout.addView(errorView, params);
    }

    /**
     * 用于自选加载的布局和位置
     * @param context
     * @param baseLayout 上一层的布局
     * @param viewIndex 父布局的位置，小于0则为最后
     */
    public ErrorLayout(Context context, ViewGroup baseLayout, int viewIndex) {
        super(context);
        errorViewExist = true;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        errorView = layoutInflater.inflate(R.layout.eb_sale_error, null);
        errorView.setId(R.id.sale_error);
        mNetErrorLl = (LinearLayout) errorView.findViewById(R.id.ll_net_error);
        mDataNullLl = (LinearLayout) errorView.findViewById(R.id.ll_data_null);
        mDataErrorLl = (LinearLayout) errorView.findViewById(R.id.ll_data_error);
        mNullCenterLl = (LinearLayout) errorView.findViewById(R.id.ll_data_null_center);
        mNullBottomLl = (LinearLayout) errorView.findViewById(R.id.ll_data_null_bottom);
        mDataNullTv = (TextView) errorView.findViewById(R.id.tv_data_null);
        mDataNullBtn = (TextView) errorView.findViewById(R.id.btn_data_null);
        mDividingLineTv = (TextView) errorView.findViewById(R.id.tv_dividing_line);
        mTipsContentTv = (TextView) errorView.findViewById(R.id.tv_tips_content);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        baseLayout.addView(errorView, viewIndex, params);
    }

    public void initError(String dataNullString){
        initError(dataNullString,"","","");
    }

    /**
     * 初始化错误数据
     * @param dataNullString 中间的文字描述
     * @param dataBtnString 按钮文字
     * @param lineString 分割线的文字
     * @param tipsString 分割线下的提示
     */
    public void initError(String dataNullString,String dataBtnString,String lineString,String tipsString){
        if(!errorViewExist){
            return;
        }
        if(!StringUtils.isNullOrEmpty(dataNullString)){
            mDataNullTv.setVisibility(View.VISIBLE);
            mDataNullTv.setText(dataNullString);
        }else{
            mDataNullTv.setVisibility(GONE);
        }
        if(!StringUtils.isNullOrEmpty(dataBtnString)){
            mDataNullBtn.setVisibility(View.VISIBLE);
            mDataNullBtn.setText(dataBtnString);
        }else{
            mDataNullBtn.setVisibility(GONE);
        }
        if(!StringUtils.isNullOrEmpty(lineString)){
            mDividingLineTv.setVisibility(View.VISIBLE);
            mDividingLineTv.setText("───────" + lineString + "───────");
        }else{
            mDividingLineTv.setVisibility(GONE);
        }
        if(!StringUtils.isNullOrEmpty(tipsString)){
            mTipsContentTv.setVisibility(View.VISIBLE);
            mTipsContentTv.setText(tipsString);
        }else{
            mTipsContentTv.setVisibility(GONE);
        }
    }

    public void getNetErrorVisible(){
        if(!errorViewExist){
            return;
        }
        errorView.setVisibility(VISIBLE);
        mNetErrorLl.setVisibility(VISIBLE);
        mDataNullLl.setVisibility(GONE);
        mDataErrorLl.setVisibility(GONE);
    }

    public void getDataNullVisible(){
        if(!errorViewExist){
            return;
        }
        errorView.setVisibility(VISIBLE);
        mNetErrorLl.setVisibility(GONE);
        mDataNullLl.setVisibility(VISIBLE);
        mDataErrorLl.setVisibility(GONE);
    }

    public void getDataErrorVisible(){
        if(!errorViewExist){
            return;
        }
        errorView.setVisibility(VISIBLE);
        mNetErrorLl.setVisibility(GONE);
        mDataNullLl.setVisibility(GONE);
        mDataErrorLl.setVisibility(VISIBLE);
    }

    public void getAllErrorGone(){
        if(!errorViewExist){
            return;
        }
        mNetErrorLl.setVisibility(GONE);
        mDataNullLl.setVisibility(GONE);
        mDataErrorLl.setVisibility(GONE);
        errorView.setVisibility(GONE);
    }

    /**
     * 部分布局上面有控件 需要与上面设置一定的间距
     * @param height 单位dp
     */
    public void setErrorViewTopMargin(int height){
        if(!errorViewExist){
            return;
        }
        setErrorViewMargin(height,0);
    }

    /**
     * 部分布局上面下面有控件 需要与其设置一定的间距
     * @param top   单位dp
     * @param bottom
     */
    public void setErrorViewMargin(int top, int bottom){
        if(!errorViewExist){
            return;
        }
        ViewGroup.LayoutParams params = errorView.getLayoutParams();
        MarginLayoutParams marginParams = null;
        //获取view的margin设置参数
        if (params instanceof MarginLayoutParams) {
            marginParams = (MarginLayoutParams) params;
        } else {
            //不存在时创建一个新的参数
            //基于View本身原有的布局参数对象
            marginParams = new MarginLayoutParams(params);
        }

        int topPx = Utils.dip2px(top);
        int bottomPx = Utils.dip2px(bottom);
        marginParams.setMargins(0, topPx, 0, bottomPx);
        errorView.setLayoutParams(marginParams);
        errorView.requestLayout();
    }

    public void setOnBtnListener(OnClickListener listener){
        if(!errorViewExist){
            return;
        }
        if(mDataNullBtn != null){
            mDataNullBtn.setOnClickListener(listener);
        }
    }

    public void setOnRefreshListener(OnClickListener listener){
        if(!errorViewExist){
            return;
        }
        if(mNetErrorLl!= null){
            mNetErrorLl.setOnClickListener(listener);
        }
        if(mDataErrorLl!= null){
            mDataErrorLl.setOnClickListener(listener);
        }
    }
}