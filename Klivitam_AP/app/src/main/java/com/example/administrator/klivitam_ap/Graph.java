package com.example.administrator.klivitam_ap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/5/20.
 */
public class Graph extends AppCompatActivity {
    //暂时用图片代替坐标系
    private ImageView mImageView;
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
            mImageView.setImageResource(R.drawable.g);
        }
        if(nameImg.equals("折线图")){
            mImageView.setImageResource(R.drawable.xhr);
        }
        if(nameImg.equals("曲线图")){
            mImageView.setImageResource(R.drawable.xx);
        }

    }
    public void initView(){
        mImageView=(ImageView) findViewById(R.id.iv);
    }
}
