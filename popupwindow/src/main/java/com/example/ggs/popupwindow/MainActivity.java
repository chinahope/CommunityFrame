package com.example.ggs.popupwindow;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;

/**
 * Created by fengjian on 2017/4/1.
 */

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox = (CheckBox) findViewById(R.id.cb);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    showPop();
                } else {
                    hidePop();
                }
            }
        });
    }

    private void showPop() {
        if(popupWindow == null){
            popupWindow = new PopupWindow(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
            View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item, null);
            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("fjdada","on popupWindow click");
                }
            });
            popupWindow.setContentView(inflate);
//            popupWindow.setFocusable(false);
//            popupWindow.setTouchable(false);
//            popupWindow.setOutsideTouchable(false);
//            popupWindow.setBackgroundDrawable(new BitmapDrawable());
        }
        popupWindow.showAsDropDown(checkBox);
    }

    private void hidePop() {
        popupWindow.dismiss();
    }
}
