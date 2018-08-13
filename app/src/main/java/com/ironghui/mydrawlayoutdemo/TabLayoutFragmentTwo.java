package com.ironghui.mydrawlayoutdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabLayoutFragmentTwo extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_popwindow, null);
        return view;
    }

    public static TabLayoutFragmentTwo newInstance() {
        TabLayoutFragmentTwo fragmentTwo = new TabLayoutFragmentTwo();
        return fragmentTwo;
    }
}
