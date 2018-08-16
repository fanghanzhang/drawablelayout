package com.ironghui.mydrawlayoutdemo.showpart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ironghui.mydrawlayoutdemo.R;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShowPartActivity extends AppCompatActivity {
    @BindView(R.id.partone)
    Button partone;
    @BindView(R.id.parttwo)
    Button parttwo;
    @BindView(R.id.framlayout)
    FrameLayout framlayout;
    private PartTwoFragment twoFragment;
    private PartOneFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showpart);
        ButterKnife.bind(this);
        initFragmentOne();
//        getDatas();
        parseJson();
    }

    private void parseJson() {
        String string = "{\"msg\":\"success\",\"result\":\"000\",\"testDetails\":[{\"isView\":true,\"name\":\"asdfsd\",\"sort\":1,\"testDetailId\":\"1029977580582731776\",\"testId\":\"1029972089827753984\",\"type\":\"1\"},{\"isView\":true,\"name\":\"eee\",\"sort\":2,\"testDetailId\":\"1029982548807122944\",\"testId\":\"1029972089827753984\",\"type\":\"2\"},{\"isView\":true,\"name\":\"dfas\",\"sort\":3,\"testDetailId\":\"1029982618638090240\",\"testId\":\"1029972089827753984\",\"type\":\"3\"},{\"isView\":true,\"name\":\"sasf\",\"sort\":4,\"testDetailId\":\"1029982701756612608\",\"testId\":\"1029972089827753984\",\"type\":\"4\"},{\"isView\":true,\"name\":\"fdsg\",\"sort\":5,\"testDetailId\":\"1029988287613239296\",\"testId\":\"1029972089827753984\",\"type\":\"3\"}]}";
        Gson gson = new Gson();
        PartBean partBean = gson.fromJson(string, PartBean.class);
        List<PartBean.TestDetailsBean> list = partBean.getTestDetails();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().equals("1")) {
                Toast.makeText(this, "拿到了1进行1的界面绘制", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initFragmentOne() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       /* if (fragment == null) {
            fragment = new PartOneFragment();
            transaction.add(R.id.framlayout, fragment);
        }
        hideFragment(transaction);
        transaction.show(fragment);*/
        if (fragment == null) {
            fragment = new PartOneFragment();
        }
        transaction.replace(R.id.framlayout, fragment);
        transaction.commit();
    }

    private void initFragmentTwo() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       /* if (twoFragment == null) {
            twoFragment = new PartTwoFragment();
            transaction.add(R.id.framlayout, twoFragment);
        }
        hideFragment(transaction);
        transaction.show(twoFragment);*/
        if (twoFragment == null) {
            twoFragment = new PartTwoFragment();
        }
        transaction.replace(R.id.framlayout, twoFragment);
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (fragment != null) {
            transaction.hide(fragment);
        }
        if (twoFragment != null) {
            transaction.hide(twoFragment);
        }
    }

    @OnClick({R.id.partone, R.id.parttwo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.partone:
                initFragmentOne();
                break;
            case R.id.parttwo:
                initFragmentTwo();
                break;
        }
    }

    public void getDatas() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("type", "A")
                        .build();
                Request request = new Request.Builder()
                        .url("http://192.168.103.116:8081/payroll/appController/test.do?")
                        .post(requestBody)
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String string = response.body().string();
                    string = "{\"msg\":\"success\",\"result\":\"000\",\"testDetails\":[{\"isView\":true,\"name\":\"asdfsd\",\"sort\":1,\"testDetailId\":\"1029977580582731776\",\"testId\":\"1029972089827753984\",\"type\":\"1\"},{\"isView\":true,\"name\":\"eee\",\"sort\":2,\"testDetailId\":\"1029982548807122944\",\"testId\":\"1029972089827753984\",\"type\":\"2\"},{\"isView\":true,\"name\":\"dfas\",\"sort\":3,\"testDetailId\":\"1029982618638090240\",\"testId\":\"1029972089827753984\",\"type\":\"3\"},{\"isView\":true,\"name\":\"sasf\",\"sort\":4,\"testDetailId\":\"1029982701756612608\",\"testId\":\"1029972089827753984\",\"type\":\"4\"},{\"isView\":true,\"name\":\"fdsg\",\"sort\":5,\"testDetailId\":\"1029988287613239296\",\"testId\":\"1029972089827753984\",\"type\":\"3\"}]}";
                    Gson gson = new Gson();
                    PartBean partBean = gson.fromJson(string, PartBean.class);
                    System.out.println(string);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
