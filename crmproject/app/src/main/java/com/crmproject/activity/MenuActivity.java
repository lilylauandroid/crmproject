package com.crmproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.crmproject.BaseActivity;
import com.crmproject.R;
import com.crmproject.util.Utils;
import com.crmproject.view.MyGridView;

public class MenuActivity extends BaseActivity {

    // 系统管理
    private MyGridView system_gridview;
    private UtilsAdapter systemAdapter;
    private int[] systemicon = {R.drawable.menu_gonggao, R.drawable.menu_mailbox, R.drawable.menu_message, R.drawable.menu_history};
    private String[] systemiconName = {"公告", "收件箱", "消息中心", "历史任务"};

    // 生产检测
    private MyGridView monitor_gridview;
    private UtilsAdapter monitorAdapter;
    private int[] monitoricon = {R.drawable.menu_xixuansystem, R.drawable.menu_peidian, R.drawable.menu_taishi, R.drawable.menu_yaliliuliang,
            R.drawable.menu_baojinglishi, R.drawable.menu_qushi, R.drawable.menu_jiankong};
    private String[] monitoriconName = {"洗选主系统", "高压配电", "生产态势", "压力流量", "设备报警历史", "实时历史趋势", "视频监控"};

    // 生产管理
    private MyGridView product_gridview;
    private UtilsAdapter productAdapter;
    private int[] producticon = {R.drawable.menu_jiaojieban, R.drawable.menu_yuexiaohao, R.drawable.menu_yibansanxun, R.drawable.menu_diaodurizhi,
            R.drawable.menu_wodepaiban, R.drawable.menu_yinhuantongji};
    private String[] producticonName = {"交接班", "月消耗", "一班三巡", "调度日志", "我的排班", "设备隐患统计"};

    // 机电管理
    private MyGridView electronic_gridview;
    private UtilsAdapter electronicAdapter;
    private int[] electronicicon = {R.drawable.menu_zichanchaxun, R.drawable.menu_shaibanfenbu, R.drawable.menu_yuejianxiu, R.drawable.menu_banzugongdan,
            R.drawable.menu_dianjianrenwu, R.drawable.menu_wentitibao, R.drawable.menu_shengchanwenti, R.drawable.menu_tingjibuhuo};
    private String[] electroniciconName = {"资产查询", "筛板分布", "月检修计划", "班组工单", "点检任务", "问题提报", "生产问题", "停机捕获"};

    // 安全管理
    private MyGridView security_gridview;
    private UtilsAdapter securityAdapter;
    private int[] securityicon = {R.drawable.menu_querendan, R.drawable.menu_wasijiance, R.drawable.menu_shigubaogao, R.drawable.menu_anquanjiangcheng,
            R.drawable.menu_yinhuanzhenggai, R.drawable.menu_weixianyuan, R.drawable.menu_gaofenxianshenqing};
    private String[] securityiconName = {"安全确认单", "瓦斯监测", "事故报告", "安全奖惩", "隐患整改", "危险源", "高风险申请"};

    // 运销管理
    private MyGridView sale_gridview;
    private UtilsAdapter saleAdapter;
    private int[] saleicon = {R.drawable.menu_rixiaoshouhuizong, R.drawable.menu_rixiaoshoujihua, R.drawable.menu_hetong};
    private String[] saleiconName = {"日销汇总", "日销售计划", "合同"};

    // 煤质管理
    private MyGridView coalquality_gridview;
    private UtilsAdapter coalqualityAdapter;
    private int[] coalqualityicon = {R.drawable.menu_meizhihuayan, R.drawable.menu_shaifenshiyan, R.drawable.menu_fuchenshiyan};
    private String[] coalqualityiconName = {"煤质化验", "筛分试验", "日常浮沉试验"};

    // 综合管理
    private MyGridView integrate_gridview;
    private UtilsAdapter integrateAdapter;
    private int[] integrateicon = {R.drawable.menu_renyuandangan, R.drawable.menu_tiaogangtiaoji, R.drawable.menu_renyuanjiangcheng, R.drawable.menu_cheliangshenqing,
            R.drawable.menu_gongjushenqing, R.drawable.menu_zhishiku};
    private String[] integrateiconName = {"人员档案", "调岗调级", "人员奖惩","车辆申请", "工具申请", "知识库"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_layout);

        init();
    }

    /**
     * 初始化
     */
    private void init() {

        // 系统管理
        system_gridview = findViewById(R.id.menu_system_gridview);
        systemAdapter = new UtilsAdapter(systemicon, systemiconName);
        system_gridview.setAdapter(systemAdapter);
        systemAdapter.notifyDataSetChanged();

        // 生产检测
        monitor_gridview = findViewById(R.id.menu_monitor_gridview);
        monitorAdapter = new UtilsAdapter(monitoricon, monitoriconName);
        monitor_gridview.setAdapter(monitorAdapter);
        monitorAdapter.notifyDataSetChanged();

        // 生产管理
        product_gridview = findViewById(R.id.menu_product_gridview);
        productAdapter = new UtilsAdapter(producticon, producticonName);
        product_gridview.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();

        // 机电管理
        electronic_gridview = findViewById(R.id.menu_electronic_gridview);
        electronicAdapter = new UtilsAdapter(electronicicon, electroniciconName);
        electronic_gridview.setAdapter(electronicAdapter);
        electronicAdapter.notifyDataSetChanged();

        // 安全管理
        security_gridview = findViewById(R.id.menu_security_gridview);
        securityAdapter = new UtilsAdapter(securityicon, securityiconName);
        security_gridview.setAdapter(securityAdapter);
        securityAdapter.notifyDataSetChanged();

        // 运销管理
        sale_gridview = findViewById(R.id.menu_sale_gridview);
        saleAdapter = new UtilsAdapter(saleicon, saleiconName);
        sale_gridview.setAdapter(saleAdapter);
        saleAdapter.notifyDataSetChanged();

        // 煤质管理
        coalquality_gridview = findViewById(R.id.menu_coalquality_gridview);
        coalqualityAdapter = new UtilsAdapter(coalqualityicon, coalqualityiconName);
        coalquality_gridview.setAdapter(coalqualityAdapter);
        coalqualityAdapter.notifyDataSetChanged();

        // 综合管理
        integrate_gridview = findViewById(R.id.menu_integrate_gridview);
        integrateAdapter = new UtilsAdapter(integrateicon, integrateiconName);
        integrate_gridview.setAdapter(integrateAdapter);
        integrateAdapter.notifyDataSetChanged();
    }

    /**
     * 菜单adapter
     */
    public class UtilsAdapter extends BaseAdapter {

        int[] mIcon;
        String[] mIconName;

        public UtilsAdapter(int[] icon, String[] iconname) {
            this.mIcon = icon;
            this.mIconName = iconname;
        }

        @Override
        public int getCount() {
            return mIconName.length;
        }

        @Override
        public String getItem(int position) {
            return mIconName[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.gv_utils_item, null);

                holder.iv_function_icon = (ImageView) convertView
                        .findViewById(R.id.iv_function_icon);
                holder.tv_function_count = (TextView) convertView
                        .findViewById(R.id.tv_function_count);
                holder.tv_function_name = (TextView) convertView
                        .findViewById(R.id.tv_function_name);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.iv_function_icon.setImageResource(mIcon[position]);
            holder.tv_function_name.setText(getItem(position));

            // 跳转
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;

                    //"公告", "消息中心", "收件箱", "历史任务"
                    if ("公告".equals(holder.tv_function_name.getText().toString())) {

                        Utils.toast(MenuActivity.this, "正在建设中^_^");

                    } else if ("消息中心".equals(holder.tv_function_name.getText().toString())) {

                        intent = new Intent(MenuActivity.this, MessageCenterActivity.class);
                        startActivity(intent);

                    } else if ("收件箱".equals(holder.tv_function_name.getText().toString())) {

                        intent = new Intent(MenuActivity.this, MailboxActivity.class);
                        startActivity(intent);

                    } else if ("交接班".equals(holder.tv_function_name.getText().toString())) {

                        intent = new Intent(MenuActivity.this, ChangeShiftsActivity.class);
                        startActivity(intent);

                    }   else if ("月消耗".equals(holder.tv_function_name.getText().toString())) {

                        intent = new Intent(MenuActivity.this, MonthConsumeActivity.class);
                        startActivity(intent);

                    } else {
                        Utils.toast(MenuActivity.this, "正在建设中^_^");
                    }
                }
            });
            return convertView;
        }

        class ViewHolder {
            ImageView iv_function_icon;
            TextView tv_function_count, tv_function_name;
        }
    }
}