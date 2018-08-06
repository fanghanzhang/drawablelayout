package com.ironghui.mydrawlayoutdemo.drawlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ironghui.mydrawlayoutdemo.R;

import java.util.ArrayList;
import java.util.List;

public class DrawLayoutActivity extends AppCompatActivity {
    private ListView listView;

    private DrawerLayout drawerLayout;

    private TextView textView;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawlayout);
        initView();

    }

    private String cli = "cli";

    private void initView() {
        btn = findViewById(R.id.btn);
        listView = findViewById(R.id.v4_listview);
        drawerLayout = findViewById(R.id.v4_drawerlayout);
        textView = findViewById(R.id.v4_text);
        initDate();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!cli.equals("")) {
                    showDrawerLayout();
                    drawerLayout.openDrawer(Gravity.LEFT);
                    cli="";
                }else {
                    drawerLayout.closeDrawers();
                    cli="cli";
                }
            }
        });


    }

    private void initDate() {
        final List<String> list = new ArrayList<String>();
        list.add("阿里");
        list.add("融汇");
        list.add("新浪");
        list.add("搜狐");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(list.get(position));
                showDrawerLayout();
            }
        });

    }

    private void showDrawerLayout() {
        if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.openDrawer(Gravity.LEFT);
        } else {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }
}
