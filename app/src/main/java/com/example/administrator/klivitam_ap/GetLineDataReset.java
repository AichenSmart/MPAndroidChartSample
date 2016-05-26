package com.example.administrator.klivitam_ap;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/26.
 */
public class GetLineDataReset extends AppCompatActivity implements View.OnClickListener {
    //确认和下一步
    private Button okBtn;
    private Button nextBtn;
    private EditText inputEdit;
    private String inputText;
    public ArrayList<String> x = new ArrayList<String>();
    public ArrayList<Entry> y = new ArrayList<Entry>();
    public ArrayList<ILineDataSet> lineDataSets = new ArrayList<ILineDataSet>();
    public LineData lineData = null;
    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_count);
        iniView();
        eventView();


    }


    private void eventView() {
        okBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
    }

    private void iniView() {
        okBtn = (Button) findViewById(R.id.input_ok);
        nextBtn = (Button) findViewById(R.id.input_next);
        inputEdit = (EditText) findViewById(R.id.input_txt);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.input_ok:
                finish();
                break;
            case R.id.input_next:
                try {
                    save(inputText);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                inputEdit.setText("");
                break;
            default:
                break;
        }
    }

    private void save(String inputText) throws IOException {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
