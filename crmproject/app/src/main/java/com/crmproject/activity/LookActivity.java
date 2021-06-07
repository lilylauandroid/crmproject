package com.crmproject.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.crmproject.BaseActivity;
import com.crmproject.R;
import com.crmproject.dashboardview.view.DashboardView;
import com.crmproject.data.CompositeIndexBean;
import com.crmproject.data.IncomeBean;
import com.crmproject.data.LineChartBean;
import com.crmproject.manager.LineChartManager;
import com.crmproject.util.LocalJsonAnalyzeUtil;
import com.crmproject.view.DayAxisValueFormatter;
import com.crmproject.view.MyAxisValueFormatter;
import com.crmproject.view.MyGridView;
import com.crmproject.view.XYMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Fill;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

public class LookActivity extends BaseActivity implements View.OnClickListener {
    private MyGridView mUtilsGv;
    private RelativeLayout rl_look_create,rl_look_jidian,rl_look_safe,rl_look_guzhang;
    private TextView tv_look_create,tv_look_jidian,tv_look_safe,tv_look_guzhang;
    private LinearLayout ll_look_create,ll_look_jidian,ll_look_safe,ll_look_guzhang;
    private TextView[] textViews =new TextView[4];
    private RelativeLayout[] rLayouts =new RelativeLayout[4];
    private LinearLayout[] lcontentLayouts =new LinearLayout[4];
    private int pos = 0;
    private String[] titleNames = { "问题及处理（月）", "前一个月跑煤率", "安全事故统计", "月均故障间隔时间（年）"};
    private TextView tv_create_data_title;
    private LineChart lineChart1;//机电，3条折线图
    private LineChartBean lineChartBean;
    private List<IncomeBean> incomeBeanList;//机电看板数据1
    private List<CompositeIndexBean> incomeBeanList2;//机电看板数据2
    private List<CompositeIndexBean> incomeBeanList3;//机电看板数据3
    private LineChartManager lineChartManager1;
    //-----------生产-------
    DashboardView dashboardView;
    //-----------安全-------
    PieChart chart;
    protected Typeface tfRegular;
    protected Typeface tfLight;
    protected final String[] parties = new String[] {"登高", "吊装", "动火", "防爆","特殊作业"};
    //--故障-
    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look);
        initData();
        initJiDianData();
        initview();
        //饼状图--安全看板--
        initSafeView();
        setSafeData(parties.length,100);
        //----故障看板   柱状图--
        initGuZhangView();
        setGuZhangData(12,100);
        //tab初始化
        changeTab(0);
    }

    private void setGuZhangData(int count, float range)  {
        {

            float start = 1f;

            ArrayList<BarEntry> values = new ArrayList<>();

            for (int i = (int) start; i < start + count; i++) {
                float val = (float) (Math.random() * (range + 1));

                if (Math.random() * 100 < 25) {
                    values.add(new BarEntry(i, val, getResources().getDrawable(R.drawable.star)));
                } else {
                    values.add(new BarEntry(i, val));
                }
            }

            BarDataSet set1;

            if (barChart.getData() != null &&
                    barChart.getData().getDataSetCount() > 0) {
                set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
                set1.setValues(values);
                barChart.getData().notifyDataChanged();
                barChart.notifyDataSetChanged();

            } else {
                set1 = new BarDataSet(values, "202106");

                set1.setDrawIcons(false);

                int startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
                int startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
                int startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
                int startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
                int startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
                int endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
                int endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple);
                int endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
                int endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
                int endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);

                int color = ContextCompat.getColor(this, R.color.blue_a3c9ff);

                List<Fill> gradientFills = new ArrayList<>();
//                gradientFills.add(new Fill(startColor1, endColor1));
//                gradientFills.add(new Fill(startColor2, endColor2));
//                gradientFills.add(new Fill(startColor3, endColor3));
//                gradientFills.add(new Fill(startColor4, endColor4));
//                gradientFills.add(new Fill(startColor5, endColor5));

                gradientFills.add(new Fill(color, color));
                set1.setFills(gradientFills);

                ArrayList<IBarDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);

                BarData data = new BarData(dataSets);
                data.setValueTextSize(10f);
//                data.setValueTypeface(tfLight);
                data.setBarWidth(0.6f);

                barChart.setData(data);
            }
        }
    }

    private void initGuZhangView() {
        barChart = findViewById(R.id.barChart);
//        barChart.setOnChartValueSelectedListener(this);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);

        barChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(true);

        barChart.setDrawGridBackground(false);
        // chart.setDrawYLabels(false);

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(barChart);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTypeface(tfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(8);
        xAxis.setValueFormatter(xAxisFormatter);

        IAxisValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = barChart.getAxisLeft();
//        leftAxis.setTypeface(tfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
//        rightAxis.setTypeface(tfLight);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
        mv.setChartView(barChart); // For bounds control
        barChart.setMarker(mv); // Set the marker to the chart
    }

    private void initData() {
        tfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
    }

    private void initview() {
//        mUtilsGv = (MyGridView) findViewById(R.id.gv_utils);
//        UtilsAdapter adapter = new UtilsAdapter();
//        mUtilsGv.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
        tv_create_data_title = findViewById(R.id.tv_create_data_title);
        lineChart1 = findViewById(R.id.lineChart);
        lineChartManager1 = new LineChartManager(lineChart1);
        //展示图表
        lineChartManager1.showLineChart(incomeBeanList, "问题数量", getResources().getColor(R.color.blue));
        lineChartManager1.addLine(incomeBeanList2, "已处理数量", getResources().getColor(R.color.red));
        lineChartManager1.addLine(incomeBeanList3, "累计待处理数量", getResources().getColor(R.color.green));

        //设置曲线填充色 以及 MarkerView
//        Drawable drawable = getResources().getDrawable(R.drawable.fade_blue);
//        lineChartManager1.setChartFillDrawable(drawable);
        lineChartManager1.setMarkerView(this);

        rl_look_create=(RelativeLayout)findViewById(R.id.rl_look_create);
        rLayouts[1]=rl_look_create;
        rl_look_jidian=(RelativeLayout)findViewById(R.id.rl_look_jidian);
        rLayouts[0]=rl_look_jidian;
        rl_look_safe=(RelativeLayout)findViewById(R.id.rl_look_safe);
        rLayouts[2]=rl_look_safe;
        rl_look_guzhang=(RelativeLayout)findViewById(R.id.rl_look_guzhang);
        rLayouts[3]=rl_look_guzhang;
        tv_look_create=(TextView) findViewById(R.id.tv_look_create);
        textViews[1]=tv_look_create;
        tv_look_jidian=(TextView) findViewById(R.id.tv_look_jidian);
        textViews[0]=tv_look_jidian;
        tv_look_safe=(TextView) findViewById(R.id.tv_look_safe);
        textViews[2]=tv_look_safe;
        tv_look_guzhang=(TextView) findViewById(R.id.tv_look_guzhang);
        textViews[3]=tv_look_guzhang;
        ll_look_create=(LinearLayout) findViewById(R.id.ll_look_create);
        lcontentLayouts[1]=ll_look_create;
        ll_look_jidian=(LinearLayout)findViewById(R.id.ll_look_jidian);
        lcontentLayouts[0]=ll_look_jidian;
        ll_look_safe=(LinearLayout)findViewById(R.id.ll_look_safe);
        lcontentLayouts[2]=ll_look_safe;
        ll_look_guzhang=(LinearLayout)findViewById(R.id.ll_look_guzhang);
        lcontentLayouts[3]=ll_look_guzhang;
        rl_look_create.setOnClickListener(this);
        rl_look_jidian.setOnClickListener(this);
        rl_look_safe.setOnClickListener(this);
        rl_look_guzhang.setOnClickListener(this);

        dashboardView = (DashboardView) findViewById(R.id.dashboardView);
        dashboardView.setTikeStrArray(new String[]{"0", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1"});
        dashboardView.setMaxNum(1);
        dashboardView.setText("概率");
//        dashboardView.setUnit("");
        dashboardView.setPercent(0.7f);

    }
    private  void initSafeView(){
        chart = findViewById(R.id.chart);
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

//        chart.setCenterTextTypeface(tfLight);
//        chart.setCenterText(generateCenterSpannableText());

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart.setRotationEnabled(false);
        chart.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
//        chart.setOnChartValueSelectedListener(this);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart.setEntryLabelColor(Color.WHITE);
//        chart.setEntryLabelTypeface(tfRegular);
        chart.setEntryLabelTextSize(12f);
    }
    private void setSafeData(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    parties[i % parties.length],
                    getResources().getDrawable(R.drawable.star)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(-400, 400));
//        dataSet.setIconsOffset(new MPPointF(-400, 400));
        dataSet.setSelectionShift(10f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
//        data.setValueTypeface(tfLight);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }
    private void initJiDianData() {
        //获取数据
        lineChartBean = LocalJsonAnalyzeUtil.JsonToObject(this, "line_chart.json", LineChartBean.class);
        incomeBeanList = lineChartBean.getGRID0().getResult().getClientAccumulativeRate();
        incomeBeanList2 = lineChartBean.getGRID0().getResult().getCompositeIndexShanghai();
        incomeBeanList3 = lineChartBean.getGRID0().getResult().getCompositeIndexShenzhen();

//        shanghai = lineChartBean.getGRID0().getResult().getCompositeIndexShanghai();
//        shenzheng = lineChartBean.getGRID0().getResult().getCompositeIndexShenzhen();
//        GEM = lineChartBean.getGRID0().getResult().getCompositeIndexGEM();

    }
    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.6f), 0, 14, 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.VORDIPLOM_COLORS[0]), 0, 14, 0);
        s.setSpan(new RelativeSizeSpan(.9f), 14, 25, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, 25, 0);
        s.setSpan(new RelativeSizeSpan(1.4f), 25, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), 25, s.length(), 0);
        return s;
    }
    private void changeTab(int pos){
        int size = rLayouts.length;
        if(pos < size){
            for (int i =0 ;i <size ;i++){
                RelativeLayout rLayout = rLayouts[i];
                TextView textView = textViews[i];
                LinearLayout linearLayout = lcontentLayouts[i];
                TextView text = linearLayout.findViewById(R.id.tv_create_data_title);
                if(pos==i){
                    rLayout.setBackground(getResources().getDrawable(R.drawable.look_tab_check_bg));
                    textView.setTextColor(getResources().getColor(R.color.red_be1e13));
                    linearLayout.setVisibility(View.VISIBLE);
                    text.setText(titleNames[i]);
                    tv_create_data_title.setText(titleNames[i]);
                }else{
                    rLayout.setBackground(null);
                    textView.setTextColor(getResources().getColor(R.color.main_tab_tv_normal));
                    linearLayout.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        changeTab(Integer.parseInt(view.getTag().toString()));
        switch (view.getId()){
            case R.id.rl_look_create:
            break;
            case R.id.rl_look_jidian:
                break;
            case R.id.rl_look_safe:
                break;
            case R.id.rl_look_guzhang:
                break;
        }
    }

//    public class UtilsAdapter extends BaseAdapter {
//
//
//        @Override
//        public int getCount() {
//            return 4;
//        }
//
//        @Override
//        public String getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            UtilsAdapter.ViewHolder holder;
//            if (convertView == null) {
//                holder = new UtilsAdapter.ViewHolder();
//                convertView = LayoutInflater.from(mContext).inflate(R.layout.gv_utils_item, null);
//
//                holder.iv_function_icon = (ImageView) convertView
//                        .findViewById(R.id.iv_function_icon);
//                holder.tv_function_count = (TextView) convertView
//                        .findViewById(R.id.tv_function_count);
//                holder.tv_function_name = (TextView) convertView
//                        .findViewById(R.id.tv_function_name);
//
//                convertView.setTag(holder);
//            } else {
//                holder = (HomeActivity.UtilsAdapter.ViewHolder) convertView.getTag();
//            }
//
//            holder.tv_function_name.setText(getItem(position));
//            if(position==1){
//                holder.tv_function_count.setVisibility(View.VISIBLE);
//                holder.tv_function_count.setText("11");
//            }else{
//                holder.tv_function_count.setVisibility(android.view.View.GONE);
//            }
//
//            holder.iv_function_icon.setImageResource(icon[position]);
//
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
//            return convertView;
//        }
//
//        class ViewHolder {
//            ImageView iv_function_icon;
//            TextView tv_function_count, tv_function_name;
//        }
//    }
}