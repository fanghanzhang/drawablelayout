package com.ironghui.mydrawlayoutdemo.showpart;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ironghui.mydrawlayoutdemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowPartThreeActivity extends AppCompatActivity implements PartThreeFragment.MyListener{

    @BindView(R.id.partone)
    Button partone;
    @BindView(R.id.parttwo)
    Button parttwo;
    @BindView(R.id.framlayout)
    FrameLayout framlayout;
    @BindView(R.id.listview)
    ListView listview;
    private FragmentManager fragmentManager;
    private PartThreeFragment myreportPerson;
    private PartFourFragment fourFragment;
    private static  int ONE = 1;
    private static  int TWO = 2;
    private static  int THREE = 3;
    private static  int FOURE = 4;
    private static  int FIVE = 5;
    private List<PartBean.TestDetailsBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showpart);
        ButterKnife.bind(this);
//        parseJson();
        fragmentManager = getFragmentManager();
        initFragmentOne();

//        initListview();
        Bundle bundle = new Bundle();
        bundle.putString("putstring", "放置进入的值");
        myreportPerson.setArguments(bundle);
    }
    private void parseJson() {
        String string = "{\"msg\":\"success\",\"result\":\"000\",\"testDetails\":[{\"isView\":true,\"name\":\"asdfsd\",\"sort\":1,\"testDetailId\":\"1029977580582731776\",\"testId\":\"1029972089827753984\",\"type\":\"1\"},{\"isView\":true,\"name\":\"eee\",\"sort\":2,\"testDetailId\":\"1029982548807122944\",\"testId\":\"1029972089827753984\",\"type\":\"2\"},{\"isView\":true,\"name\":\"dfas\",\"sort\":3,\"testDetailId\":\"1029982618638090240\",\"testId\":\"1029972089827753984\",\"type\":\"3\"},{\"isView\":true,\"name\":\"sasf\",\"sort\":4,\"testDetailId\":\"1029982701756612608\",\"testId\":\"1029972089827753984\",\"type\":\"4\"},{\"isView\":true,\"name\":\"fdsg\",\"sort\":5,\"testDetailId\":\"1029988287613239296\",\"testId\":\"1029972089827753984\",\"type\":\"3\"}]}";
        Gson gson = new Gson();
        PartBean partBean = gson.fromJson(string, PartBean.class);
        list = partBean.getTestDetails();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().equals("1")) {
                Toast.makeText(this, "拿到了1进行1的界面绘制", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void initListview() {
        PartAdapter adapter = new PartAdapter(this,list);
        listview.setAdapter(adapter);
    }

    private void initFragmenttwo() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        if (fourFragment == null) {
            fourFragment = new PartFourFragment();
            transaction.add(R.id.framlayout, fourFragment);
        } else {
            transaction.show(fourFragment);
        }
        transaction.commitAllowingStateLoss();
    }

    private void initFragmentOne() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        if (myreportPerson == null) {
            myreportPerson = new PartThreeFragment();
            transaction.add(R.id.framlayout, myreportPerson);
        } else {
            transaction.show(myreportPerson);
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (myreportPerson != null) {
            transaction.hide(myreportPerson);
        }
        if (fourFragment != null) {
            transaction.hide(fourFragment);
        }
    }

    @OnClick({R.id.partone, R.id.parttwo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.partone:
                initFragmentOne();
                break;
            case R.id.parttwo:
                initFragmenttwo();
                break;
        }
    }

    @Override
    public void getString(String str) {
        Log.d("listener",str.toString());
    }
}
