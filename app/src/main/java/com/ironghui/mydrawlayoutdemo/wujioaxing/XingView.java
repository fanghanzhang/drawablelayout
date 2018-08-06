package com.ironghui.mydrawlayoutdemo.wujioaxing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class XingView extends View {
    public XingView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mDeafultPaint = new Paint();
        int mViewWidth = getWidth();
        int mViewHeight = getHeight();
        mDeafultPaint.setAntiAlias(true);//抗锯齿
        canvas.translate(mViewWidth / 2, mViewHeight / 2);//移动画布到页面中心

//        五角星外接圆大小
        int t = mViewWidth / 3;
//        五角星五个角的角度360/5

        float r = 72;
//        弧度的计算公式为： 2*PI/360*角度；
        double aa = 2 * PI / 360;

        canvas.rotate(-90);

//        计算5个顶点坐标
        float a[] = {(float) (Math.cos(0 * aa) * t), (float) (sin(0 * aa) * t)};
        float b[] = {(float) (Math.cos(r * aa) * t), (float) (sin(r * aa) * t)};
        float c[] = {(float) (Math.cos(r * 2 * aa) * t), (float) (-sin(r * 2 * aa) * t)};
        float d[] = {(float) (Math.cos(r * 3 * aa) * t), (float) (-sin(r * 3 * aa) * t)};
        float e[] = {(float) (Math.cos(r * 4 * aa) * t), (float) (sin(r * 4 * aa) * t)};


//        五角星阴影
        mDeafultPaint.setStrokeWidth(2);
        mDeafultPaint.setColor(Color.BLACK);
        mDeafultPaint.setStyle(Paint.Style.FILL);
        Path path1 = new Path();
        path1.moveTo(a[0] - 100, a[1] + 100);
        path1.lineTo(d[0], d[1]);
        path1.lineTo(e[0] - 50, e[1] + 100);
        path1.lineTo(b[0] - 50, b[1] + 50);
        path1.lineTo(c[0], c[1]);
        path1.close();
        canvas.drawPath(path1, mDeafultPaint);


//        画五角星
        mDeafultPaint.setStrokeWidth(2);
        mDeafultPaint.setColor(Color.YELLOW);
        mDeafultPaint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.moveTo(a[0], a[1]);
        path.lineTo(d[0], d[1]);
        path.lineTo(e[0], e[1]);
        path.lineTo(b[0], b[1]);
        path.lineTo(c[0], c[1]);
        path.close();
        canvas.drawPath(path, mDeafultPaint);


//        中心到顶点的连线
        mDeafultPaint.setStrokeWidth(1);
        mDeafultPaint.setColor(Color.BLACK);
        mDeafultPaint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(0, 0, a[0], a[1], mDeafultPaint);
        canvas.drawLine(0, 0, b[0], b[1], mDeafultPaint);
        canvas.drawLine(0, 0, c[0], c[1], mDeafultPaint);
        canvas.drawLine(0, 0, d[0], d[1], mDeafultPaint);
        canvas.drawLine(0, 0, e[0], e[1], mDeafultPaint);

//        五角星内外接圆大小
        double tt = t * sin(18 * aa) / sin((180 - 36 - 18) * aa);
//        内接圆
//        canvas.drawCircle(0, 0, (float) tt, mDeafultPaint);
        int rr = 36;
        //        计算5个交叉点坐标
        float a1[] = {(float) (Math.cos(rr * aa) * tt), (float) (sin(rr * aa) * tt)};
        float b1[] = {(float) (Math.cos(rr * 3 * aa) * tt), (float) (sin(rr * 3 * aa) * tt)};
        float c1[] = {(float) (Math.cos(rr * 5 * aa) * tt), (float) (-sin(rr * 5 * aa) * tt)};
        float d1[] = {(float) (Math.cos(rr * 7 * aa) * tt), (float) (sin(rr * 7 * aa) * tt)};
        float e1[] = {(float) (Math.cos(rr * 9 * aa) * tt), (float) (sin(rr * 9 * aa) * tt)};

//        中心到交叉点的连线
        mDeafultPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, a1[0], a1[1], mDeafultPaint);
        canvas.drawLine(0, 0, b1[0], b1[1], mDeafultPaint);
        canvas.drawLine(0, 0, c1[0], c1[1], mDeafultPaint);
        canvas.drawLine(0, 0, d1[0], d1[1], mDeafultPaint);
        canvas.drawLine(0, 0, e1[0], e1[1], mDeafultPaint);

    }
}
