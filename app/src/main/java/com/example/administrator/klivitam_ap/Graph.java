package com.example.administrator.klivitam_ap;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/20.
 */
public class Graph extends AppCompatActivity {
    //暂时用图片代替坐标系
    private RadarChart mChart;
    private Typeface tf;
    private String[] mParties = new String[] {
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I"
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_window);
        initView();
        viewControl();
    }
    public void viewControl(){
        Bundle bundle = this.getIntent().getExtras();;
        String nameImg=bundle.getString("样式");
        //暂时没做坐标系，先用图表代替。
        if(nameImg.equals("雷达图")){
            //描述
            mChart.setDescription("雷达图  ");
            // 绘制线条宽度，圆形向外辐射的线条
            mChart.setWebLineWidth(1.5f);
            // 内部线条宽度，外面的环状线条
            mChart.setWebLineWidthInner(1.0f);
            // 所有线条WebLine透明度
            mChart.setWebAlpha(100);
            setData();

            XAxis xAxis = mChart.getXAxis();
            xAxis.setTypeface(tf); // x坐标值样式
            xAxis.setTextSize(12f);
            YAxis yAxis = mChart.getYAxis();
            yAxis.setTypeface(tf);
            yAxis.setLabelCount(6, false);
            yAxis.setTextSize(15f);
            yAxis.setStartAtZero(true);
            Legend l = mChart.getLegend();
            l.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
            l.setTypeface(tf);
            l.setXEntrySpace(2f);
            l.setYEntrySpace(1f);

        }
        if(nameImg.equals("折线图")){

        }
        if(nameImg.equals("曲线图")){

        }

    }
    public void initView(){
        mChart=(RadarChart) findViewById(R.id.radar_chart);
    }
    public void setData(){
        float mult  = 150;
        int cnt = 9;
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        for (int i = 0; i < cnt; i++) {
            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
        }
        for (int i = 0; i < cnt; i++) {
            yVals2.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
        }
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < cnt; i++)
            xVals.add(mParties[i % mParties.length]);
        RadarDataSet set1 = new RadarDataSet(yVals1, "Set 1");
        set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set1.setDrawFilled(true);
        set1.setLineWidth(2f);
        RadarDataSet set2 = new RadarDataSet(yVals2, "Set 2");
        set2.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);
        set2.setDrawFilled(true);
        set2.setLineWidth(2f);
        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        sets.add(set2);


        RadarData data = new RadarData(xVals,  sets);
        /* 数据字体样式 */
        data.setValueTextSize(8f);
        // 是否绘制Y值到图表
        data.setDrawValues(true);
        mChart.setData(sets);

        mChart.invalidate();
    }


}
