package com.helloworld.andapitest.activity.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by babycomingin100days on 2017/6/28.
 */
public class CanvasBase extends View {
    protected Paint mPaint = new Paint();
    public CanvasBase(Context context) {
        super(context);
    }

    public CanvasBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);
    }
}
