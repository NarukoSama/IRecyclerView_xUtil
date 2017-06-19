package com.bwie.day0618_oldweekexam_aaa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private CircleView circleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleView = (CircleView) findViewById(R.id.circleview);

        circleView.setBackgroundColor(Color.BLUE);
        circleView.setWidth(200);
        circleView.setHeight(200);

        circleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,TwoActivity.class);
                startActivity(intent);

            }
        });


    }
}
