package com.ironghui.mydrawlayoutdemo.scrollviewandlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            onTouchEvent(ev);
            return false;
        }
        return true;
    }

}
