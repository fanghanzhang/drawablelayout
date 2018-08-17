package com.ironghui.mydrawlayoutdemo.showpart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ironghui.mydrawlayoutdemo.R;

import java.util.List;

public class PartAdapter extends BaseAdapter {
    private Context mCOntext;
    private List<PartBean.TestDetailsBean> list;
    private OneHolder oneHolder;
    private TwoHolder twoHolder;

    public PartAdapter(Context context, List<PartBean.TestDetailsBean> list) {
        this.mCOntext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        switch (type) {
            case 1:
            case 2:
                if (convertview == null) {
                    convertview = LayoutInflater.from(mCOntext).inflate(R.layout.listview_item, viewGroup, false);
                    oneHolder = new OneHolder();
                    oneHolder.textview = convertview.findViewById(R.id.textview);
                    convertview.setTag(oneHolder);
                } else {
                    convertview.getTag();
                }
                oneHolder.textview.setText(list.get(i).getName());
                break;
            case 3:
            case 4:
                if (convertview == null) {
                    convertview = LayoutInflater.from(mCOntext).inflate(R.layout.fragment_parttwo, viewGroup, false);
                    twoHolder = new TwoHolder();
                    twoHolder.texttpartone = convertview.findViewById(R.id.texttpartone);
                    convertview.setTag(twoHolder);
                } else {
                    convertview.getTag();
                }
                twoHolder.texttpartone.setText(list.get(i).getTestDetailId());
                break;
        }
        return convertview;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType().equals("1")) {
            return 1;
        } else if (list.get(position).getType().equals("2")) {
            return 2;
        } else if (list.get(position).getType().equals("3")) {
            return 3;
        } else if (list.get(position).getType().equals("4")) {
            return 4;
        }
        return 5;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    class OneHolder {
        private TextView textview;
    }

    class TwoHolder {
        private TextView texttpartone;
    }
}
