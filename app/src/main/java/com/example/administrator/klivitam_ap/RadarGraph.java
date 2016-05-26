package com.example.administrator.klivitam_ap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.github.mikephil.charting.animation.Easing;
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
public class RadarGraph extends AppCompatActivity implements View.OnClickListener{
    private RadarChart mChart;
    private Button mBtn;

    //导入数据的方式
    private Button feInput; //外部文件导入
    private Button rdInput; //随机导入
    private Button arInput; //自己定义数组导入
    private Button dbInput; //数据库导入
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_radarchart);
        initView();
        viewControl();
        BtnEvent();
    }

    private void BtnEvent() {
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RadarGraph.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void viewControl(){
            mChart.setDescription("");
            mChart.setWebLineWidth(1.5f);
            mChart.setWebLineWidthInner(0.75f);
            mChart.setWebAlpha(100);
            setData();
            mChart.animateXY(
                    1400, 1400,
                    Easing.EasingOption.EaseInOutQuad,
                    Easing.EasingOption.EaseInOutQuad);

            XAxis xAxis = mChart.getXAxis();
            xAxis.setTextSize(9f);

            YAxis yAxis = mChart.getYAxis();
            yAxis.setLabelCount(5, false);
            yAxis.setTextSize(9f);
            yAxis.setAxisMinValue(0f);

            Legend l = mChart.getLegend();
            l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
            l.setXEntrySpace(7f);
            l.setYEntrySpace(5f);
    }
    public void initView(){
        mChart=(RadarChart) findViewById(R.id.radar_chart);
        mBtn= (Button) findViewById(R.id.back_img);
    }
    private String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I"
    };
    public void setData() {

        float mult = 150;
        int cnt = 9;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
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
        set1.setFillColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set1.setDrawFilled(true);
        set1.setLineWidth(2f);

        RadarDataSet set2 = new RadarDataSet(yVals2, "Set 2");
        set2.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);
        set2.setFillColor(ColorTemplate.VORDIPLOM_COLORS[4]);
        set2.setDrawFilled(true);
        set2.setLineWidth(2f);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(xVals, sets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        mChart.setData(data);
        mChart.invalidate();
    }


    @Override
    public void onClick(View view) {

    }
}
