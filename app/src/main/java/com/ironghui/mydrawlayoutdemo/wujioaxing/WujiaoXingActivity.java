package com.ironghui.mydrawlayoutdemo.wujioaxing;

import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.Toast;

import com.ironghui.mydrawlayoutdemo.R;

public class WujiaoXingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wujiaoxing);
//        findViewById(R.id.fiveview);
//        findViewById(R.id.xingview);
        final RatingBar mRatingBar = (RatingBar) findViewById(R.id.mratingbar);
        final ViewStar star = new ViewStar(this);
        LayerDrawable layerDrawable = (LayerDrawable) mRatingBar.getProgressDrawable();

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(WujiaoXingActivity.this, "你给出了" + rating + "星好评", Toast.LENGTH_SHORT).show();
//                mRatingBar.setRating(rating);
//                mRatingBar.setMax(60);
                float r = mRatingBar.getRating();
            }
        });

    }
}
