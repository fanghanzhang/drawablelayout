package com.ironghui.mydrawlayoutdemo.wujioaxing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ironghui.mydrawlayoutdemo.R;

public class ViewStar extends View {
    public static final int MAX_STAR = 5;

    public ViewStar(@NonNull Context context) {
        this(context, null);
    }

    public ViewStar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewStar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.starview);
//        mRating = typedArray.getFloat(R.styleable.starView_rating, 0);
        typedArray.recycle();
        init();
    }

    Paint paint;
    Bitmap starYellow;
    Bitmap starGray;
    float mRating;
    int starWidth;
    int starHeight;
    int gap;

    private void init() {
        paint = new Paint();
        starYellow = BitmapFactory.decodeResource(getResources(), R.drawable.bianji);
        starGray = BitmapFactory.decodeResource(getResources(), R.drawable.sousuo);
        starWidth = starYellow.getWidth();
        starHeight = starYellow.getHeight();
        gap = 5;
        invalidate();
    }

    public void setRating(float rating) {
        this.mRating = rating;
        invalidate();
    }

    public void setGrayStar(int resId, int alpha) {
        starGray = BitmapFactory.decodeResource(getResources(), resId);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = (getPaddingLeft() + (starWidth + gap) * MAX_STAR + getPaddingRight());
        int height = (getPaddingTop() + starHeight + getPaddingBottom());
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width,
                heightMode == MeasureSpec.EXACTLY ? heightSize : height);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float currentRating = mRating < 0 ? 0 : (mRating > MAX_STAR ? MAX_STAR : mRating);
        int mLeft = 0;
        int mTop = 0;
        int full = (int) currentRating;/*整个星星的数量*/
        /**
         * 画黄色的整颗星
         */
        for (int i = 0; i < full; i++) {
            canvas.drawBitmap(starYellow, mLeft, mTop, paint);
            mLeft = mLeft + starWidth + gap;
        }

        if (currentRating == MAX_STAR) {
            return;
        }
        /**
         * 画灰色的整颗星
         */
        for (int i = full; i < MAX_STAR; i++) {
            canvas.drawBitmap(starGray, mLeft, mTop, paint);
            mLeft = mLeft + starWidth + gap;
        }

        /**
         * 画小数点部分的星
         */
        float part = mRating - full;
        if (part > 0) {
            int w = (int) (part * starWidth);
            Bitmap partBitmap = Bitmap.createBitmap(starYellow, 0, 0, w, starYellow.getHeight());
            canvas.drawBitmap(partBitmap, full * (starWidth + gap), mTop, paint);
        }

    }
}
