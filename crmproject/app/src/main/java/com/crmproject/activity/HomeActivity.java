package com.crmproject.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.crmproject.BaseActivity;
import com.crmproject.MyApplication;
import com.crmproject.R;
import com.crmproject.data.CompositeIndexBean;
import com.crmproject.data.IncomeBean;
import com.crmproject.data.LineChartBean;
import com.crmproject.manager.LineChartManager;
import com.crmproject.util.LocalJsonAnalyzeUtil;
import com.crmproject.util.StringUtils;
import com.crmproject.util.Utils;
import com.crmproject.view.MyGridView;
import com.github.mikephil.charting.charts.LineChart;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
 public class HomeActivity extends BaseActivity {
    private MyGridView mUtilsGv;

     // 图片封装为一个数组
     private int[] icon = { R.drawable.home_menu_1, R.drawable.home_menu_2,
             R.drawable.home_menu_3, R.drawable.home_menu_4, R.drawable.home_menu_5,
             R.drawable.home_menu_6 };
     private String[] iconName = { "代办任务", "任务消息", "洗选主系统", "停机捕获", "月消耗", "交接班"};
     private LineChart lineChart1,lineChart2,lineChart3,lineChart4;//机电，3条折线图
     private LineChartBean lineChartBean;
     private List<IncomeBean> incomeBeanList;//机电看板数据1
     private List<CompositeIndexBean> incomeBeanList2;//机电看板数据2
     private List<CompositeIndexBean> incomeBeanList3;//机电看板数据3
     private LineChartManager lineChartManager1,lineChartManager2,lineChartManager3,lineChartManager4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initLineChar1Data();
        initview();
        initLineChar1();
    }

     private void initLineChar1() {
         lineChart1 = findViewById(R.id.lineChart1);
         lineChartManager1 = new LineChartManager(lineChart1);
         List<IncomeBean> secondList = new ArrayList<>();
         for(CompositeIndexBean bean: incomeBeanList2){
             IncomeBean incomeBean = new IncomeBean();
             incomeBean.setTradeDate(bean.getTradeDate());
             incomeBean.setValue(bean.getRate());
             secondList.add(incomeBean);
         }
         List<IncomeBean> threeList = new ArrayList<>();
         for(CompositeIndexBean bean: incomeBeanList3){
             IncomeBean incomeBean = new IncomeBean();
             incomeBean.setTradeDate(bean.getTradeDate());
             incomeBean.setValue(bean.getRate());
             threeList.add(incomeBean);
         }
         //展示图表
         lineChartManager1.showLineChart(secondList, "生产检测", getResources().getColor(R.color.blue));
//         lineChartManager1.addLine(incomeBeanList2, "已处理数量", getResources().getColor(R.color.red));
//         lineChartManager1.addLine(incomeBeanList3, "累计待处理数量", getResources().getColor(R.color.green));
         lineChartManager1.setMarkerView(this);

         lineChart2 = findViewById(R.id.lineChart2);
         lineChartManager2 = new LineChartManager(lineChart2);
         //展示图表
         lineChartManager2.showLineChart(incomeBeanList, "主井皮带电流", getResources().getColor(R.color.red));
//         lineChartManager1.addLine(incomeBeanList2, "已处理数量", getResources().getColor(R.color.red));
//         lineChartManager1.addLine(incomeBeanList3, "累计待处理数量", getResources().getColor(R.color.green));
         lineChartManager2.setMarkerView(this);

         lineChart3 = findViewById(R.id.lineChart3);
         lineChartManager3 = new LineChartManager(lineChart3);
         lineChartManager3.showLineChart(threeList, "瞬时产率", getResources().getColor(R.color.blue));
         lineChartManager3.addLine(incomeBeanList2, "当日产率", getResources().getColor(R.color.green));
//         lineChartManager1.addLine(incomeBeanList3, "累计待处理数量", getResources().getColor(R.color.green));
         lineChartManager3.setMarkerView(this);

         lineChart4 = findViewById(R.id.lineChart4);
         lineChartManager4 = new LineChartManager(lineChart4);
         lineChartManager4.showLineChart(incomeBeanList, "307密度", getResources().getColor(R.color.blue));
//         lineChartManager4.addLine(incomeBeanList2, "3109密度", getResources().getColor(R.color.green));
         lineChartManager4.addLine(incomeBeanList3, "3109密度", getResources().getColor(R.color.green));
         lineChartManager4.setMarkerView(this);
     }
     private void initLineChar1Data() {
         //获取数据
         lineChartBean = LocalJsonAnalyzeUtil.JsonToObject(this, "line_chart.json", LineChartBean.class);
         incomeBeanList = lineChartBean.getGRID0().getResult().getClientAccumulativeRate();
         incomeBeanList2 = lineChartBean.getGRID0().getResult().getCompositeIndexShanghai();
         incomeBeanList3 = lineChartBean.getGRID0().getResult().getCompositeIndexShenzhen();
     }

     private void initview() {
        mUtilsGv = (MyGridView) findViewById(R.id.gv_utils);
        UtilsAdapter adapter = new UtilsAdapter();
        mUtilsGv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    /**
     * 更新常用工具布局
     */
//    private void updateFunctions() {
//        mUtilsGv.setAdapter(new UtilsAdapter(MyApplication.getSelf().functionEntity.functionlist));
//        if (mApp.functionEntity.tablelist != null && mApp.functionEntity.tablelist.size() > 0) {
//            if (!StringUtils.isNullOrEmpty(mApp.functionEntity.tablelist.get(0).tablename)) {
//                tvTitle.setText(mApp.functionEntity.tablelist.get(0).tablename);
//            }
//        }
//    }
    public class UtilsAdapter extends BaseAdapter {


        @Override
        public int getCount() {
        return iconName.length;
    }

        @Override
        public String getItem(int position) {
        return iconName[position];
    }

        @Override
        public long getItemId(int position) {
        return position;
    }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
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

        holder.tv_function_name.setText(getItem(position));
        if(position==0){
            holder.tv_function_count.setVisibility(View.VISIBLE);
            holder.tv_function_count.setText("11");
        }else{
            holder.tv_function_count.setVisibility(android.view.View.GONE);
        }

        holder.iv_function_icon.setImageResource(icon[position]);
            // 跳转
            holder.iv_function_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    if (position == 0) { // 待办任务
                        intent = new Intent(HomeActivity.this, MailboxActivity.class);
                        startActivityForAnima(intent);
                    } else if (position == 1) { // 任务消息
                        intent = new Intent(HomeActivity.this, MessageCenterActivity.class);
                        startActivityForAnima(intent);
                    } else if (position == 4) { // 月消耗
                        intent = new Intent(HomeActivity.this, MonthConsumeActivity.class);
                        startActivityForAnima(intent);
                    } else if (position == 5) { // 交接班
                        intent = new Intent(HomeActivity.this, ChangeShiftsActivity.class);
                        startActivityForAnima(intent);
                    } else {
                        Utils.toast(HomeActivity.this, "正在建设中^_^");
                    }

                }
            });

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        return convertView;
    }

        class ViewHolder {
            ImageView iv_function_icon;
            TextView tv_function_count, tv_function_name;
        }
    }
}