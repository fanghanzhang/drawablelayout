package com.ironghui.mydrawlayoutdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabLayoutActivity extends AppCompatActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tab.addTab(tab.newTab().setText("你看"));
        tab.addTab(tab.newTab().setText("你看"));
        tab.addTab(tab.newTab().setText("你看"));
        tab.addTab(tab.newTab().setText("你看"));
        tab.addTab(tab.newTab().setText("你看"));
        tab.addTab(tab.newTab().setText("你看"));
        tab.addTab(tab.newTab().setText("你看"));
        tab.addTab(tab.newTab().setText("你再看"));
    }
}
