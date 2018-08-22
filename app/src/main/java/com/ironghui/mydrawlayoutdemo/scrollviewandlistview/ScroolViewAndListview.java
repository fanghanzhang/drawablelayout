package com.ironghui.mydrawlayoutdemo.scrollviewandlistview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.ironghui.mydrawlayoutdemo.ItemType;
import com.ironghui.mydrawlayoutdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScroolViewAndListview extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    private List<ItemType> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroolviewandlistview);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        mList = new ArrayList<ItemType>();
        mList.add(new ItemType("王麻子", "1", "789"));
        mList.add(new ItemType("李四", "0", "963"));
        mList.add(new ItemType("嗲高了", "0", "123"));
        mList.add(new ItemType("吗啡", "1", "1203"));
        mList.add(new ItemType("王麻子", "1", "789"));
        mList.add(new ItemType("李四", "0", "963"));
        mList.add(new ItemType("嗲高了", "0", "123"));
        mList.add(new ItemType("吗啡", "1", "1203"));
        mList.add(new ItemType("王麻子", "1", "789"));
        mList.add(new ItemType("李四", "0", "963"));
        mList.add(new ItemType("嗲高了", "0", "123"));
        mList.add(new ItemType("吗啡", "1", "1203"));
        mList.add(new ItemType("王麻子", "1", "789"));
        mList.add(new ItemType("李四", "0", "963"));
        mList.add(new ItemType("嗲高了", "0", "123"));
        mList.add(new ItemType("吗啡", "1", "1203"));
        mList.add(new ItemType("王麻子", "1", "789"));
        mList.add(new ItemType("李四", "0", "963"));
        mList.add(new ItemType("嗲高了", "0", "123"));
        mList.add(new ItemType("吗啡", "1", "1203"));
        mList.add(new ItemType("王麻子", "1", "789"));
        mList.add(new ItemType("李四", "0", "963"));
        mList.add(new ItemType("嗲高了", "0", "123"));
        mList.add(new ItemType("吗啡", "1", "1203"));
        mList.add(new ItemType("王麻子", "1", "789"));
        mList.add(new ItemType("李四", "0", "963"));
        mList.add(new ItemType("嗲高了", "0", "123"));
        mList.add(new ItemType("吗啡", "1", "1203"));
    }

    private void initView() {
        ScroolViewAndListviewAdapter adapter = new ScroolViewAndListviewAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(adapter);
    }

    //setAdapter()方法之前控制高度
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
