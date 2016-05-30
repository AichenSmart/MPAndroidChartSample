package com.example.android_cj;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by Administrator on 2016/5/26.
 */
public class RecordActivity extends AppCompatActivity implements View.OnClickListener{
    private XAxis xAxis;         //X坐标轴
    private YAxis yAxis;         //Y坐标轴
    private Timer timer = new Timer(true);
/*    private Handler handler  = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                getLineData(20 + 1);
                mLineChart.invalidate();
            }
        }
    };*/
    private TextView mFood;
    private ImageView mFoodImage;
    private ImageView mImage;
    public int mCount = 0;
    private LineChart mLineChart;
    private Button mNextBtn;
    private Chronometer mTimer = null;
    public ArrayList<String> x = new ArrayList<String>();
    public ArrayList<Entry> y = new ArrayList<Entry>();
    public ArrayList<ILineDataSet> lineDataSets = new ArrayList<ILineDataSet>();
    public LineData lineData = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_main);
        initView();
        eventView();
        LineData resultLineData = getLineData(20);
        showChart();
    }
    private LineData getLineData(int count) {
        for (int i = 0; i < count; i++) {  //X轴显示的数据
            x.add(i + "");
        }
        for (int i = 0; i < count; i++) {//y轴的数据
            float result = (float) (Math.random() * 200) + 3;
            y.add(new Entry(result, i));
        }
        LineDataSet lineDataSet = new LineDataSet(y, "折线图");//y轴数据集合
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawCubic(true);
        lineDataSet.setCubicIntensity(0.1f);
        lineDataSet.setLineWidth(2f);//线宽
        lineDataSet.setCircleSize(4f);//现实圆形大小
        lineDataSet.setColor(Color.RED);//现实颜色
        lineDataSet.setCircleSize(Color.BLUE);//圆形颜色
        lineDataSet.setHighLightColor(Color.BLACK);//高度线的颜色
        lineDataSets.add(lineDataSet);
        lineData = new LineData(x,lineDataSets);
        return lineData;
    }
    public void showChart() {

        mLineChart.setVisibleXRangeMaximum(8);

        LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setTextSize(12f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setSpaceBetweenLabels(1);

        LimitLine ll = new LimitLine(100f, null);
        ll.setLineColor(Color.RED);
        ll.setLineWidth(4f);
        ll.setTextColor(Color.GRAY);
        ll.setTextSize(12f);
        xAxis.addLimitLine(ll);

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaxValue(200f);
        leftAxis.setAxisMinValue(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);
        mLineChart.getAxisRight().setDrawGridLines(false);
        mLineChart.getAxisLeft().setDrawGridLines(false);
        mLineChart.getXAxis().setDrawGridLines(false);
        //警戒线
 /*       LimitLine ll = new LimitLine(100f, "警戒线");
        ll.setLineColor(Color.RED);
        ll.setLineWidth(4f);
        ll.setTextColor(Color.GRAY);
        ll.setTextSize(12f);*/
        mLineChart.setDrawBorders(false);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setDescription("时间");//数据描述
        mLineChart.setBackgroundColor(Color.CYAN);//背景颜色
        mLineChart.setData(lineData);//设置数据
        Legend legend = mLineChart.getLegend();//设置比例图片标示，就是那一组Y的value
        legend.setForm(Legend.LegendForm.CIRCLE);//样式
        legend.setFormSize(6f);//字体
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        legend.setTextColor(Color.WHITE);//设置颜色
        mLineChart.animateX(2000);//X轴的动画
//        timer.schedule(task, 0, 1);
    }

    private void eventView() {
        mImage.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
    }

    /**
     * 1.绑定控件
     * 2.屏幕适配
     */
    private void initView() {
        mImage=(ImageView) findViewById(R.id.top_back);
        mNextBtn=(Button) findViewById(R.id.next_step);
        mTimer=(Chronometer) findViewById(R.id.top_time);
        mLineChart=(LineChart) findViewById(R.id.chart_line);
        mFoodImage=(ImageView) findViewById(R.id.food_img);
        mFood=(TextView) findViewById(R.id.food_bz);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int w = metric.widthPixels;
        int h = metric.heightPixels;
        LinearLayout.LayoutParams image = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        image.height = h/6;
        image.width = h/6;
        mFoodImage.setLayoutParams(image);
        LinearLayout.LayoutParams text = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        text.height = h / 4;
        text.width = w/2;
        mFood.setLayoutParams(text);
    }

    /**
     * 记住还没改点击事件的用线程
     * @param v
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_back:
                finish();
                break;
            case R.id.next_step:
                if(mCount==0) {
                    mNextBtn.setText("下一步");
                    mTimer.start();
                    mCount++;
                }
               mTimer.setBase(SystemClock.elapsedRealtime());
                break;
        }

    }
/*    private TimerTask task = new TimerTask() {
        public void run() {
            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);
        }
    };*/
}
