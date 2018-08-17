package com.ironghui.mydrawlayoutdemo.showpart;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.ironghui.mydrawlayoutdemo.R;

public class PartThreeFragment extends Fragment {

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view =inflater.inflate(R.layout.fragment_parttwo,null);
        View view = inflater.inflate(R.layout.fragment_parttwo, container, false);
        listView = view.findViewById(R.id.part_twolistview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
