package com.ironghui.mydrawlayoutdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ironghui.mydrawlayoutdemo.adapter.ListViewAdapter;
import com.ironghui.mydrawlayoutdemo.scrollviewandlistview.MyListView;
import com.ironghui.mydrawlayoutdemo.scrollviewandlistview.MyScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewActivity extends AppCompatActivity {
    @BindView(R.id.lv)
    MyListView lv;
    @BindView(R.id.scrollview)
    MyScrollView scrollview;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ButterKnife.bind(this);
        scrollview.smoothScrollTo(0, 0);
        setTouchEvent();
        initView();
    }

    private void setTouchEvent() {

        lv.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                lv.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

    }

    private void initView() {
        ListViewAdapter listViewAdapter = new ListViewAdapter(this);
        lv.setAdapter(listViewAdapter);
//        setListViewHeight(lv);//解决scroview嵌套listview布局显示
        lv.setFocusable(false);//解决当第一次进入界面动态加载listview的i数据后，ScrollView会跳滑到listview的第一个子项。
    }

    public void setListViewHeight(ListView listView) {
        if (listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
