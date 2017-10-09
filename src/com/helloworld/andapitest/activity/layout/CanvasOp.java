package com.helloworld.andapitest.activity.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by babycomingin100days on 2017/6/27.
 */
public class CanvasOp extends View {

    private Paint mPaint = new Paint();

    public CanvasOp(Context context) {
        super(context);
    }

    public CanvasOp(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasOp(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPaint();
//        canvas.translate(200,200);
        canvas.drawColor(Color.argb(150, 255, 0, 0));
//        canvas.save();

//        canvas.drawCircle(0,0,100,mPaint);
//
//        mPaint.setColor(Color.BLUE);
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,100,mPaint);
//        mPaint.setColor(Color.argb(255,200,100,100));
//        canvas.drawLine(-400,-400,-200,-200,mPaint);
//        mPaint.setColor(Color.WHITE);
//        canvas.drawLine(-200,-200,0,0,mPaint);

        //scale
        canvas.translate(getWidth() / 2, getHeight() / 2);
        mPaint.setColor(Color.WHITE);
        canvas.drawLines(new float[]{-getWidth()/2,0,getWidth()/2,0,0,-getHeight()/2,0,getHeight()/2},mPaint);
        canvas.drawRect(0, -getHeight() / 4, getWidth() / 4, 0, mPaint);
        mPaint.setColor(Color.BLUE);
        mPaint.setAlpha(50);
        canvas.scale(0.5f, 0.5f);//缩小一倍
        canvas.drawRect(0, -getHeight() / 4, getWidth() / 4, 0, mPaint);
        canvas.scale(2f, 2f);//放大一倍
        canvas.drawRect(-getWidth() / 4, -getHeight() / 4, 0, 0, mPaint);
        canvas.scale(0.5f, 0.5f,-getWidth()/8,0);
        mPaint.setColor(Color.RED);
        canvas.drawPoint(-getWidth()/8,0,mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(-getWidth() / 4, -getHeight() / 4, 0, 0, mPaint);
        canvas.scale(-1,-1,0,0);
        mPaint.setColor(Color.BLACK);
        canvas.drawPoint(0,0,mPaint);
        canvas.drawRect(-getWidth() / 4, -getHeight() / 4, 0, 0, mPaint);
        canvas.save();
        drawBtfCircle(canvas);
        canvas.restore();
        mPaint.setColor(Color.YELLOW);
        canvas.drawPoint(0,0,mPaint);
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);
    }

    private void drawBtfCircle(Canvas canvas){
        canvas.translate(-getWidth()/8,0);
        canvas.drawCircle(0,0,getWidth(),mPaint);
        canvas.drawCircle(0,0,(float)(getWidth()*0.9),mPaint);
        for(int i=0;i<=360;i+=10){
            canvas.drawLine(0,getWidth(),0,(float)(getWidth()*0.9),mPaint);
            canvas.rotate(10);
        }
    }

}
