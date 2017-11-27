package com.example.ggs.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnBoardRecommendInfo info = new OnBoardRecommendInfo();
        OnBoardRecommendInfo infoNull = null;
        if (info.equals(infoNull)) {
            Log.e("fjdada", " equals");
        }else {
            Log.e("fjdada", " no equals");
        }
    }
}
