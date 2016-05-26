package com.example.administrator.klivitam_ap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/24.
 */
public class LineGraph extends AppCompatActivity implements View.OnClickListener {

    private LineChart mLineChart;
    private Button mBack;
    public ArrayList<String> x = new ArrayList<String>();
    public ArrayList<Entry> y = new ArrayList<Entry>();
    public ArrayList<ILineDataSet> lineDataSets = new ArrayList<ILineDataSet>();
    public LineData lineData = null;
    public static final int UPDATE_1 = 1;
    //导入数据的方式
    private Button feInput; //外部文件导入
    private Button rdInput; //随机导入
    private Button arInput; //自己定义数组导入
    private Button dbInput; //数据库导入*/

    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_linechart);

        mLineChart = (LineChart) findViewById(R.id.line_chart);
        feInput = (Button) findViewById(R.id.fiel_inpt);
        rdInput = (Button) findViewById(R.id.random_input);
        arInput = (Button) findViewById(R.id.array_input);
        dbInput = (Button) findViewById(R.id.db_input);
        mBack=(Button) findViewById(R.id.back_img);
        btnEvent();

        LineData resultLineData = getLineData();
        showChart();
    }

    private LineData getLineData() {
        for (int i = 0; i < 60; i++) {  //X轴显示的数据
            x.add(i + "");
        }
        for (int i = 0; i < 60; i++) {//y轴的数据
            float result = (float) (Math.random() * 100) + 3;
            y.add(new Entry(result, i));
        }
        LineDataSet lineDataSet = new LineDataSet(y, "折线图");//y轴数据集合
        lineDataSet.setLineWidth(1f);//线宽
        lineDataSet.setCircleSize(2f);//现实圆形大小
        lineDataSet.setColor(Color.RED);//现实颜色
        lineDataSet.setCircleSize(Color.BLUE);//圆形颜色
        lineDataSet.setHighLightColor(Color.WHITE);//高度线的颜色
        lineDataSets.add(lineDataSet);
        lineData = new LineData(x,lineDataSets);
        return lineData;
    }
    public void showChart() {
        mLineChart.setDrawBorders(false);//是否添加边框
        mLineChart.setDescription("有风险的数据");//数据描述
        mLineChart.setDrawGridBackground(true);//是否显示表格颜色
        mLineChart.setBackgroundColor(Color.DKGRAY);//背景颜色
        mLineChart.setData(lineData);//设置数据
        Legend legend = mLineChart.getLegend();//设置比例图片标示，就是那一组Y的value
        legend.setForm(Legend.LegendForm.CIRCLE);//样式
        legend.setFormSize(6f);//字体
        legend.setTextColor(Color.WHITE);//设置颜色
        mLineChart.animateX(2000);//X轴的动画
    }

    private void btnEvent() {
        rdInput.setOnClickListener(this);
        arInput.setOnClickListener(this);
        dbInput.setOnClickListener(this);
        mBack.setOnClickListener(this);
        feInput.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.array_input:
                x.clear();
                y.clear();
                Intent intent=new Intent(LineGraph.this,GetLineDataReset.class);
                startActivity(intent);
                break;
            case R.id.db_input:


                break;

            case R.id.random_input:
                x.clear();
                y.clear();
                getLineData();
                showChart();
                break;

            case R.id.fiel_inpt:

                break;

            default:
                break;


        }

    }
}