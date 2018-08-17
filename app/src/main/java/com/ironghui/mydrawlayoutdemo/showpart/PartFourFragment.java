package com.ironghui.mydrawlayoutdemo.showpart;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ironghui.mydrawlayoutdemo.R;

public class PartFourFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_partone, null);
        View view = inflater.inflate(R.layout.fragment_partone, container,false);

        return view;
    }
}
