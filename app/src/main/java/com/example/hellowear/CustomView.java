package com.example.hellowear;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {

    private static final int SQUARE_SIZE = 50;
    private int mSquareColor;
    private int mSquareSize;

    private Rect mRectSquare;
    private Paint mPaintSquare;

    private Paint mPaintCircle;

    private float mCircleX, mCircleY, mCircleRadius=50f;

    public CustomView(Context context) {
        super(context);

        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    public void swapColor()
    {
        mPaintSquare.setColor(mPaintSquare.getColor() == mSquareColor? Color.RED : mSquareColor);
        postInvalidate();
    }

    private void init(@Nullable AttributeSet set){
        mRectSquare = new Rect();
        mPaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setStrokeWidth(50f);
        mPaintCircle.setColor(Color.GREEN);

        if(set == null){
            return;
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustomView);

        mSquareColor = typedArray.getColor(R.styleable.CustomView_square_color, Color.RED);
        mSquareSize = typedArray.getDimensionPixelSize(R.styleable.CustomView_square_size, SQUARE_SIZE);

        mPaintSquare.setColor(mSquareColor);

        typedArray.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        mRectSquare.left = 20;
        mRectSquare.right = mRectSquare.left+mSquareSize;
        mRectSquare.top = 20;
        mRectSquare.bottom = mRectSquare.top +mSquareSize;

        canvas.drawRect(mRectSquare,mPaintSquare);

        float cx,cy;
        float radius = 50f;
        cx = getWidth() - radius - 10f;
        cy = mRectSquare.top + (mRectSquare.height()/2);

        if(mCircleX == 0f || mCircleY == 0f){
            mCircleX = getWidth() - radius - 10f;
            mCircleY = mRectSquare.top + (mRectSquare.height()/2);
        }

        canvas.drawCircle(mCircleX,mCircleY,mCircleRadius, mPaintCircle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value =  super.onTouchEvent(event);
        switch ((event.getAction()))
        {
            case MotionEvent.ACTION_DOWN: {

                float x = event.getX();
                float y = event.getY();

                if(mRectSquare.left < x && mRectSquare.right > x)
                {
                    if(mRectSquare.top < y && mRectSquare.bottom > y)
                    {
                        mCircleRadius += 10f;
                        postInvalidate();
                    }
                }
                return true;
            }

            case MotionEvent.ACTION_MOVE: {

                float x = event.getX();
                float y = event.getY();

                double dx = Math.pow(x - mCircleX, 2);
                double dy = Math.pow(y - mCircleY, 2);

                if(dx+dy < Math.pow(mCircleRadius,2)){
                    mCircleX = x;
                    mCircleY = y;

                    postInvalidate();;
                    return true;
                }

                return value;
            }
        }

        return  value;

    }
}
