package com.helloworld.andapitest.activity.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by babycomingin100days on 2017/6/27.
 */
public class SloopView extends View {

    private Paint mPaint = new Paint();

    public SloopView(Context context) {
        super(context);
    }

    public SloopView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SloopView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initPaint();
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        canvas.drawPoint(200, 200, mPaint);     //在坐标(200,200)位置绘制一个点
        canvas.drawPoints(new float[]{          //绘制一组点，坐标位置由float数组指定
                getWidth() / 2, getHeight() / 2,
        }, mPaint);

//        canvas.drawLine(300,300,500,600,mPaint);    // 在坐标(300,300)(500,600)之间绘制一条直线
        canvas.drawLines(new float[]{               // 绘制一组线 每四数字(两个点的坐标)确定一条线
                100,200,200,200,
                100,300,200,300
        },mPaint);

//        canvas.drawRect(100,100,500,500,mPaint);
        RectF rectF = new RectF(0,0,getWidth(),getHeight());
//        canvas.drawRoundRect(rectF,30,30,mPaint);

//        RectF rectF = new RectF(100,100,500,550);
//        canvas.drawOval(rectF,mPaint);

//        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,mPaint);

        canvas.drawArc(rectF,0,90,true,mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawArc(rectF,90,90,true,mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawArc(rectF,180,90,true,mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawArc(rectF,270,90,true,mPaint);


    }

    private void setBackground(Canvas canvas) {

    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
    }

}
