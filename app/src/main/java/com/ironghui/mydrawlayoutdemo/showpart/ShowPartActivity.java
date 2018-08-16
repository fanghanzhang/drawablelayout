package com.ironghui.mydrawlayoutdemo.showpart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ironghui.mydrawlayoutdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
}
