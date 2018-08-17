package com.ironghui.mydrawlayoutdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ironghui.mydrawlayoutdemo.ItemType;
import com.ironghui.mydrawlayoutdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<ItemType> mList;
    private Context mCOntext;
    private View view1;
    private MyViewHolder myViewHolder;
    private TypeViewHodler typeViewHodler;
    private static final int typee = 0;
    private static final int typeetwo = 1;

    public ListViewAdapter(Context context) {
        this.mCOntext = context;
        mList = new ArrayList<ItemType>();
        mList.add(new ItemType("王麻子", "1", "789"));
        mList.add(new ItemType("李四", "0", "963"));
        mList.add(new ItemType("嗲高了", "0", "123"));
        mList.add(new ItemType("吗啡", "1", "1203"));
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        switch (type) {
            case typee:
                if (convertView == null) {
                    convertView = LayoutInflater.from(mCOntext).inflate(R.layout.listview_item, viewGroup, false);
                    myViewHolder = new MyViewHolder();
                    myViewHolder.textView = convertView.findViewById(R.id.textview);
                    myViewHolder.typee = convertView.findViewById(R.id.typee);
                    convertView.setTag(myViewHolder);
                } else {
                    convertView.getTag();
                }
                myViewHolder.textView.setText(mList.get(i).getName());
                myViewHolder.typee.setText(mList.get(i).getType()+"type");
                break;

            case typeetwo:
                if (convertView == null) {
                    convertView = LayoutInflater.from(mCOntext).inflate(R.layout.listview, viewGroup, false);
                    typeViewHodler = new TypeViewHodler();
                    typeViewHodler.typeText = convertView.findViewById(R.id.type);
                    convertView.setTag(typeViewHodler);
                } else {
                    convertView.getTag();
                }
                typeViewHodler.typeText.setText(mList.get(i).getName() + "你在看");
                break;
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position).getType().equals("0")) {
            return typee;
        } else if (mList.get(position).getType().equals("1")) {
            return typeetwo;
        }
        return typeetwo;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    class MyViewHolder {
        private TextView typee;
        private TextView textView;
    }

    class TypeViewHodler {
        private TextView typeText;
    }
}
