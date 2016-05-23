package com.example.administrator.klivitam_ap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private Button mWebBtn;
    private ListView mListGraph;
    private EditText mEditTextSearch;
    String[] names = {"雷达图", "折线图", "曲线图", "柱状图", "xx图", "xx图", "xx图", "xx图", "xx图", "xx图"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        event();
        ListControl();
        mWebBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,WebView_sea.class);
                String url= "http://"+mEditTextSearch.getText()+".com";
                intent.putExtra("URL",url);
                startActivity(intent);
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    //以后规范会之后集中放置点击事件
    public void event() {
    }
    //对listview的一系列操作先集中放置在这里。
    public void ListControl() {
        mListGraph.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, names));
        mListGraph.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (names[arg2].equals("雷达图")) {
                    Intent intent = new Intent(MainActivity.this, Graph.class);
                    intent.putExtra("样式",names[arg2]);
                    startActivity(intent);
                }
                if (names[arg2].equals("折线图")) {
                    Intent intent = new Intent(MainActivity.this, Graph.class);
                    intent.putExtra("样式",names[arg2]);
                    startActivity(intent);
                }
                if (names[arg2].equals("曲线图")) {
                    Intent intent = new Intent(MainActivity.this, Graph.class);
                    intent.putExtra("样式",names[arg2]);
                    startActivity(intent);
                }
                if (names[arg2].equals("柱状图")) {
                    Intent intent = new Intent(MainActivity.this, Graph.class);
                    intent.putExtra("样式",names[arg2]);
                    startActivity(intent);
                }
            }
        });
    }

    //试图初始化
    public void initView() {
        mListGraph = (ListView) findViewById(R.id.GraphList);
        mEditTextSearch = (EditText) findViewById(R.id.Ed_search);
        mWebBtn=(Button) findViewById(R.id.btnGraph);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
