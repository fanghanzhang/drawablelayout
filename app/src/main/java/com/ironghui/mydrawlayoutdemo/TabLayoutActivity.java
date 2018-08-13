package com.ironghui.mydrawlayoutdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
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
     /*   tab.addTab(tab.newTab().setText("你看"));
        tab.addTab(tab.newTab().setText("你0看"), 0);
        tab.addTab(tab.newTab().setText("你看"));
        tab.addTab(tab.newTab().setText("你6看"));
        tab.addTab(tab.newTab().setText("你7看"), 3);
        tab.addTab(tab.newTab().setText("你8看"), 2);
        tab.addTab(tab.newTab().setText("你再看"), 1);*/
        tvTitle.setText("标题处");
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        LinearLayout mLinearLayout = (LinearLayout) tab.getChildAt(0);
// 在所有子控件的中间显示分割线（还可能只显示顶部、尾部和不显示分割线）
        mLinearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
// 设置分割线的距离本身（LinearLayout）的内间距
        mLinearLayout.setDividerPadding(20);
// 设置分割线的样式
        mLinearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.divider_vertical));
        TabLayoutAdapter adapter = new TabLayoutAdapter(getSupportFragmentManager(), "你看", "腻子看");
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
    }
}
