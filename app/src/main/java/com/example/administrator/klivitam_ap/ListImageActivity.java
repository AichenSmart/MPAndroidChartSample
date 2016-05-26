package com.example.administrator.klivitam_ap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/25.
 */
public class ListImageActivity extends AppCompatActivity{
    private ListView mListImage;
    private ListView mListImage1;
    private int []mimages={R.drawable.g,R.drawable.xhr,R.drawable.xx};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listimage);
        mListImage=(ListView) findViewById(R.id.list_image);
        mListImage1=(ListView) findViewById(R.id.list_image2);
        SimpleAdapter adapter1 = new SimpleAdapter(this,getData(),R.layout.image2,
                new String[]{"img"},
                new int[]{R.id.img});
        mListImage1.setAdapter(adapter1);
        SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.image,
                new String[]{"img"},
                new int[]{R.id.img});
        mListImage.setAdapter(adapter);
    }
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < 3; i++) {
            map.put("img", R.drawable.g);
            mList.add(map);
        }
        return mList;
    }
}
