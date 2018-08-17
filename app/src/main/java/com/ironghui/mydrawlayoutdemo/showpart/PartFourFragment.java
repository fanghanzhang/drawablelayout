package com.ironghui.mydrawlayoutdemo.showpart;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ironghui.mydrawlayoutdemo.R;

public class PartFourFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_partone, null);
        View view = inflater.inflate(R.layout.fragment_partone, container,false);

        return view;
    }

    /**
     * activity 中oncreate方法调用后走此方法
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
   /*     Bundle bundle = getArguments();
        String s = bundle.getString("putstring");
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();*/
    }
}
