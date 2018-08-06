package com.ironghui.mydrawlayoutdemo.popwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ironghui.mydrawlayoutdemo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopWindowActivity extends AppCompatActivity {

    private ArrayList list = new ArrayList();
    @BindView(R.id.btpop)
    Button btpop;
    private PopupWindow mPopupWindow;
    private View mPopView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popwindow);
        ButterKnife.bind(this);
        initDate();
        initPopWindow();
    }

    private void initPopWindow() {
        mPopView = getLayoutInflater().inflate(R.layout.popwindow_layout, null);
        ListView listView = mPopView.findViewById(R.id.lv);
        // 将转换的View放置到 新建一个popuwindow对象中
        MyAdapter myAdapter = new MyAdapter(this, list);
        mPopupWindow = new PopupWindow(mPopView,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        // 点击popuwindow外让其消失
        listView.setAdapter(myAdapter);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setContentView(mPopView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(PopWindowActivity.this, i + "onItemClick", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initDate() {
        list.add("0000000");
        list.add("1111111111");
        list.add("22222222222");
        list.add("0000000");
        list.add("1111111111");
        list.add("22222222222");
        list.add("0000000");
        list.add("1111111111");
        list.add("22222222222");
        list.add("0000000");
    }

    @OnClick(R.id.btpop)
    public void onViewClicked() {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
//            mPopupWindow.showAsDropDown(btpop);
            mPopupWindow.showAtLocation(mPopView, Gravity.BOTTOM, 0, 300);
        }
    }

    class MyAdapter extends BaseAdapter {
        private Context context;
        private ArrayList list;

        public MyAdapter(Context context, ArrayList list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = LayoutInflater.from(PopWindowActivity.this).inflate(R.layout.item, null);
                vh.textview = (TextView) convertView.findViewById(R.id.tv_text);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.textview.setText(list.get(position) + "000");
            return convertView;
        }

        class ViewHolder {
            TextView textview;
        }
    }
}
