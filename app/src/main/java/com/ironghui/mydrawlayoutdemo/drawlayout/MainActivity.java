package com.ironghui.mydrawlayoutdemo.drawlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ironghui.mydrawlayoutdemo.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testmain);
        SlidingMenu slidingMenu = new SlidingMenu(this);
//        SlidingMenu   slidingMenu = new SlidingMenu(this, SlidingMenu.SLIDING_WINDOW);//两参数的构造函数中已经添加attachToActivity（）该方法
        //菜单布局
        slidingMenu.setMenu(R.layout.leftitem);
        // 菜单宽度
        int menuWidth = getResources().getDisplayMetrics().widthPixels / 4;
        slidingMenu.setBehindWidth(menuWidth);
        // 菜单位置
        slidingMenu.setMode(SlidingMenu.LEFT);
        // 滑出方式
        slidingMenu.setTouchModeAbove(SlidingMenu.LEFT);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);//一个参数的构造方法中无添加至activity的方法。 。。与上述注释二选一，缺一不可
    }
}
