package com.ironghui.mydrawlayoutdemo.showpart;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ironghui.mydrawlayoutdemo.R;

import java.util.List;

public class PartFourFragment extends Fragment {
    private List<PartBean.TestDetailsBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_partone, null);
        View view = inflater.inflate(R.layout.fragment_partone, container, false);
        parseJson();
        return view;
    }

    /**
     * activity 中oncreate方法调用后走此方法
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
   /*     Bundle bundle = getArguments();
        String s = bundle.getString("putstring");
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();*/
        parseJson();
    }

    private void parseJson() {
        String string = "{\"msg\":\"success\",\"result\":\"000\",\"testDetails\":[{\"isView\":true,\"name\":\"asdfsd\",\"sort\":1,\"testDetailId\":\"1029977580582731776\",\"testId\":\"1029972089827753984\",\"type\":\"1\"},{\"isView\":true,\"name\":\"eee\",\"sort\":2,\"testDetailId\":\"1029982548807122944\",\"testId\":\"1029972089827753984\",\"type\":\"2\"},{\"isView\":true,\"name\":\"dfas\",\"sort\":3,\"testDetailId\":\"1029982618638090240\",\"testId\":\"1029972089827753984\",\"type\":\"3\"},{\"isView\":true,\"name\":\"sasf\",\"sort\":4,\"testDetailId\":\"1029982701756612608\",\"testId\":\"1029972089827753984\",\"type\":\"4\"},{\"isView\":true,\"name\":\"fdsg\",\"sort\":5,\"testDetailId\":\"1029988287613239296\",\"testId\":\"1029972089827753984\",\"type\":\"3\"}]}";
        Gson gson = new Gson();
        PartBean partBean = gson.fromJson(string, PartBean.class);
        list = partBean.getTestDetails();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().equals("1")) {
                Toast.makeText(getActivity(), " 拿到了type(1)进行1的界面绘制", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
