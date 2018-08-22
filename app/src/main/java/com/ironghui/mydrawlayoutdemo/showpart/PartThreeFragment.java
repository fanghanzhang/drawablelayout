package com.ironghui.mydrawlayoutdemo.showpart;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ironghui.mydrawlayoutdemo.R;

import java.util.List;

public class PartThreeFragment extends Fragment {
    private List<PartBean.TestDetailsBean> list;
    private ListView listView;
    private  MyListener myListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myListener = (MyListener) getActivity();//创建接口的子类对象，activity实现了MyListener，activity即为子类对象
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parttwo, null);
//        View view = inflater.inflate(R.layout.fragment_parttwo, container, false);//较常用，fragment的布局会添加至activity中，第三个参数就为false
        parseJson();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.part_twolistview);
        listView.setAdapter(new PartAdapter(getActivity(), list));
        myListener.getString("fragment回调传值给activity");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        parseJson();
    }

    private void parseJson() {
        String string = "{\"msg\":\"success\",\"result\":\"000\",\"testDetails\":[{\"isView\":true,\"name\":\"asdfsd\",\"sort\":1,\"testDetailId\":\"1029977580582731776\",\"testId\":\"1029972089827753984\",\"type\":\"1\"},{\"isView\":true,\"name\":\"eee\",\"sort\":2,\"testDetailId\":\"1029982548807122944\",\"testId\":\"1029972089827753984\",\"type\":\"2\"},{\"isView\":true,\"name\":\"dfas\",\"sort\":3,\"testDetailId\":\"1029982618638090240\",\"testId\":\"1029972089827753984\",\"type\":\"3\"},{\"isView\":true,\"name\":\"sasf\",\"sort\":4,\"testDetailId\":\"1029982701756612608\",\"testId\":\"1029972089827753984\",\"type\":\"4\"},{\"isView\":true,\"name\":\"fdsg\",\"sort\":5,\"testDetailId\":\"1029988287613239296\",\"testId\":\"1029972089827753984\",\"type\":\"3\"}]}";
        Gson gson = new Gson();
        PartBean partBean = gson.fromJson(string, PartBean.class);
        list = partBean.getTestDetails();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().equals("1")) {
                Toast.makeText(getActivity(), "拿到了type(1)进行1的界面绘制", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private  void setMyListener(MyListener listener) {
       this.myListener = listener;
    }

    public interface MyListener {
        void getString(String str);
    }
}
