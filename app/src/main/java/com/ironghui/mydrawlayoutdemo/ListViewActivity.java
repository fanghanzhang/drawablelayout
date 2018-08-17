package com.ironghui.mydrawlayoutdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.ironghui.mydrawlayoutdemo.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewActivity extends AppCompatActivity {
    @BindView(R.id.lv)
    ListView lv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ListViewAdapter listViewAdapter = new ListViewAdapter(this);
        lv.setAdapter(listViewAdapter);
    }
}
