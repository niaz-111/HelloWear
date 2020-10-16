package com.example.hellowear;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private CustomView mCustomView;
    private CustomUI mCustomUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();

        mCustomUI = new CustomUI(this);




//        mCustomView = (CustomView) findViewById(R.id.customView);
//        findViewById(R.id.button_swap_color).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mCustomView.swapColor();
//            }
//        });




    }
}
