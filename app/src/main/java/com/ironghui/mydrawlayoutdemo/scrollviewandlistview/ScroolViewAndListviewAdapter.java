package com.ironghui.mydrawlayoutdemo.scrollviewandlistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ironghui.mydrawlayoutdemo.ItemType;
import com.ironghui.mydrawlayoutdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ScroolViewAndListviewAdapter extends RecyclerView.Adapter<ScroolViewAndListviewAdapter.MyViewHodler> {
    private Context mContext;
    private List<ItemType> mList;
    private MyViewHodler viewHodler;

    public ScroolViewAndListviewAdapter(Context context) {
        this.mContext = context;
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

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false);
        viewHodler = new MyViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int i) {
        holder.textview.setText(mList.get(i).getName());
        holder.typee.setText(mList.get(i).getType() + "type");
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder {

        private TextView textview;
        private TextView typee;

        public MyViewHodler(View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.textview);
            typee = itemView.findViewById(R.id.typee);
        }
    }
}
