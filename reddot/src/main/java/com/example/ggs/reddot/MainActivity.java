package com.example.ggs.reddot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Reddot r1, r2, r1_2, r1_3, r1_4, r1_2_1, r1_2_2;
    boolean b1_2_1 = true, b1_2_2 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View tv1 = findViewById(R.id.tv_1);
        tv1.setOnClickListener(this);
        r1 = new Reddot(tv1);

        View tv2 = findViewById(R.id.tv_2);
        tv2.setOnClickListener(this);
        r2 = new Reddot(tv2);

        View tv1_2 = findViewById(R.id.tv_1_2);
        tv1_2.setOnClickListener(this);
        r1_2 = new Reddot(tv1_2);

        View tv1_3 = findViewById(R.id.tv_1_3);
        tv1_3.setOnClickListener(this);
        r1_3 = new Reddot(tv1_3);

        View tv1_4 = findViewById(R.id.tv_1_4);
        tv1_4.setOnClickListener(this);
        r1_4 = new Reddot(tv1_4);

        View tv1_2_1 = findViewById(R.id.tv_1_2_1);
        tv1_2_1.setOnClickListener(this);
        r1_2_1 = new Reddot(tv1_2_1);

        View tv1_2_2 = findViewById(R.id.tv_1_2_2);
        tv1_2_2.setOnClickListener(this);
        r1_2_2 = new Reddot(tv1_2_2);

        r1.addChild(r1_2);
        r1.addChild(r1_3);
        r1.addChild(r1_4);

        r1_2.addChild(r1_2_1);
        r1_2.addChild(r1_2_2);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_1:
                r1.change(View.VISIBLE);
                break;
            case R.id.tv_2:
                r2.change(View.VISIBLE);
                break;
            case R.id.tv_1_2:
                r1_2.change(View.VISIBLE);
                break;
            case R.id.tv_1_3:
                r1_3.change(View.VISIBLE);
                break;
            case R.id.tv_1_4:
                r1_4.change(View.VISIBLE);
                break;
            case R.id.tv_1_2_1:
                r1_2_1.change(b1_2_1 ? View.VISIBLE : View.GONE);
                b1_2_1 = !b1_2_1;
                break;
            case R.id.tv_1_2_2:
                r1_2_2.change(b1_2_2 ? View.VISIBLE : View.GONE);
                b1_2_2 = !b1_2_2;
                break;
        }
    }
}
