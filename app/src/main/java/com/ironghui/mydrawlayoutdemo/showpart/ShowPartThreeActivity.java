package com.ironghui.mydrawlayoutdemo.showpart;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ironghui.mydrawlayoutdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowPartThreeActivity extends AppCompatActivity {

    @BindView(R.id.partone)
    Button partone;
    @BindView(R.id.parttwo)
    Button parttwo;
    @BindView(R.id.framlayout)
    FrameLayout framlayout;
    private FragmentManager fragmentManager;
    private PartThreeFragment myreportPerson;
    private PartFourFragment fourFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showpart);
        ButterKnife.bind(this);
        fragmentManager = getFragmentManager();
        initFragmentOne();
    }

    private void initFragmenttwo() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        if(fourFragment==null){
            fourFragment = new PartFourFragment();
            transaction.add(R.id.framlayout, fourFragment);
        }else{
            transaction.show(fourFragment);
        }
        transaction.commitAllowingStateLoss();
    }

    private void initFragmentOne() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        if(myreportPerson==null){
            myreportPerson = new PartThreeFragment();
            transaction.add(R.id.framlayout, myreportPerson);
        }else{
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
}
