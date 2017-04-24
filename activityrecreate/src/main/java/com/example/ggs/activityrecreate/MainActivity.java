package com.example.ggs.activityrecreate;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private int mTheme;
    private String THEME = "theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mTheme = savedInstanceState.getInt(THEME);
            switchTheme(mTheme);
        }

        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

        Log.e(MainActivity.class.getName(), "onCreate");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.e(MainActivity.class.getName(), "onSaveInstanceState");
        savedInstanceState.putInt(THEME, mTheme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(MainActivity.class.getName(), "onRestoreInstanceState");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(MainActivity.class.getName(), "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(MainActivity.class.getName(), "onResume");

    }

    private void switchTheme(int theme) {
        switch (mTheme) {
            case android.R.style.Theme_Holo_Light:
                mTheme = android.R.style.Theme_Black_NoTitleBar;
                break;
            case android.R.style.Theme_Black_NoTitleBar:
                mTheme = android.R.style.Theme_Holo_Light;
                break;
            default:
                mTheme = android.R.style.Theme_Holo_Light;
                break;
        }
        setTheme(mTheme);
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        recreate();
    }

    @Override
    protected void onDestroy() {
        Log.e(MainActivity.class.getName(), "onDestroy");
        super.onDestroy();
    }
}
