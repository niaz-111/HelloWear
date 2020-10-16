package com.example.hellowear;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;


public class CustomUI extends View{

    private Paint mCircleAround;

    private Paint mTimePaint;
    private Paint mWatchPaint;
    private Paint mStepPaint;
    private Paint mStartPaint;
    private Paint mPathPaint;
    private Paint mWatchSecPaint;
    private Paint mRectPaint;
    private Paint mPaintProgress;

    private Path mPath;
    private RectF mRect;
    private RectF mRectProgress;
    float mStartAngle = 270f;
    float mSweepAngle = 0f;
    Rect bounds;


    String stepString = "step";
    int stepCount = 0;
    String startString = "START";

    CountDownTimer counter;
    String hourString = "12";
    String minuteString = "00";

    int minute = 0;

    Handler h;

    String time = hourString+":"+minuteString;



    public CustomUI(Context context) {
        super(context);
        init(null);

    }

    public CustomUI(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public CustomUI(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public CustomUI(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);

    }

    private void init(@Nullable AttributeSet set){
       mTimePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
       mTimePaint.setStyle(Paint.Style.FILL);
       mTimePaint.setColor(Color.BLUE);
       mTimePaint.setTextSize(25f);
       mTimePaint.setTextAlign(Paint.Align.CENTER);

        mWatchPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWatchPaint.setStyle(Paint.Style.FILL);
        mWatchPaint.setColor(Color.WHITE);
        mWatchPaint.setTextSize(60f);
        mWatchPaint.setTextAlign(Paint.Align.CENTER);



        mWatchSecPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWatchSecPaint.setStyle(Paint.Style.FILL);
        mWatchSecPaint.setColor(Color.WHITE);
        mWatchSecPaint.setTextSize(15f);
        mWatchSecPaint.setTextAlign(Paint.Align.LEFT);

        mStepPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStepPaint.setStyle(Paint.Style.FILL);
        mStepPaint.setColor(Color.GRAY);
        mStepPaint.setTextSize(25f);
        mStepPaint.setTextAlign(Paint.Align.CENTER);

        mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPathPaint.setColor(getResources().getColor(R.color.pathColor));
        mPathPaint.setStyle(Paint.Style.FILL);


        mPath = new Path();

        mCircleAround = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleAround.setStyle(Paint.Style.STROKE);
        mCircleAround.setStrokeWidth(10f);
        mCircleAround.setColor(Color.BLACK);



        mRect = new RectF();

        mStartPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStartPaint.setTextAlign(Paint.Align.CENTER);
        mStartPaint.setTextSize(15f);

        mRectProgress = new RectF();


        mPaintProgress = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintProgress.setStyle(Paint.Style.STROKE);
        mPaintProgress.setStrokeWidth(10f);
        mPaintProgress.setColor(Color.BLUE);

         counter  = new CountDownTimer(36000, 1000) {
            @Override
            public void onTick(long l) {
                mSweepAngle += 10f;
                stepCount += 1;
                if(stepCount != 0)
                {
                    stepString = "steps";
                }
                if(stepCount%36 == 0)
                {

                    onFinish();
                    cancel();
                }
                postInvalidate();
            }

            @Override
            public void onFinish() {
                Toast.makeText(getContext(),"You've Completed Target",Toast.LENGTH_SHORT).show();
                mSweepAngle=0;
                startString = "START";
                postInvalidate();

            }
        };

//         Runnable runnable = new Runnable() {
//             @Override
//             public void run() {
////                 Log.v("Custom", "Hello");
//                 Toast.makeText(getContext(),"Hello",Toast.LENGTH_SHORT).show();
//                 h.postDelayed(this, 1000);
//             }
//         };

         h = new Handler();

         Runnable runnable = new Runnable() {
             @Override
             public void run() {
                 h.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         Date date = new Date();
                         time = date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
                         postInvalidate();
                         h.postDelayed(this, 1000);
                     }
                 }, 1000);
             }
         };


         Thread thread =  new Thread(runnable);
         thread.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        float x = getWidth()/2;
        float posWatch = getHeight()/2;

        canvas.drawText("0 / 30 mins",x,posWatch-5f-60f,mTimePaint);
        canvas.drawText(time, x,posWatch , mWatchPaint);


       // canvas.drawText("05",(float)(bounds.width())/2+2f,posWatch,mWatchSecPaint);
        canvas.drawText(stepString+" "+Integer.toString(stepCount), x, posWatch+5f+35f,mStepPaint);

        mRect.bottom = getHeight();
        mRect.top = getHeight()-70f;
        mRect.left = 50f;
        mRect.right = getWidth()-50f;

        mRectProgress.set(0f,0f,getWidth(),getHeight());


        canvas.drawRoundRect(mRect,50f,30f,mPathPaint);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2-1f,mCircleAround);
        canvas.drawArc(mRectProgress,mStartAngle,mSweepAngle,false, mPaintProgress);
        canvas.drawText(startString,(mRect.left+mRect.right)/2,(mRect.top+mRect.bottom)/2,mStartPaint);

//        mPath.moveTo(0f, getHeight()-30f);
//        mPath.lineTo(70f,getHeight()-70f);
//        mPath.lineTo(getWidth()-70f,getHeight()-70f);
//        mPath.lineTo(getWidth(),getHeight()-30f);
//        mPath.lineTo(0f,getHeight()-30f);
//
//        canvas.drawPath(mPath, mPathPaint);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value =  super.onTouchEvent(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
            {
                float xx = event.getX();
                float yy = event.getY();

                if(mRect.left < xx &&  mRect.right > xx)
                {
                    if(mRect.top < yy && mRect.bottom > yy)
                    {

                            if(startString.equals("START"))
                            {
                                counter.start();
                                startString = "STOP";
                            }
                            else
                            {
                                counter.cancel();
                                Toast.makeText(getContext(),"You Haven't reached target", Toast.LENGTH_SHORT).show();
                                startString = "START";
                            }
                            postInvalidate();

                    }

                }

                return  true;

            }
            case MotionEvent.ACTION_MOVE:
            {
                    return  true;
            }
        }
        return  value;
    }
}
