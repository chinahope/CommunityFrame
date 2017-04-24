package com.example.ggs.imagespan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    String targetStr = "/@";
    TextView hello;
    TextView imageSpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClickSpan();
        initImageSpan();
    }

    private void initView() {
        hello = (TextView) findViewById(R.id.hello);
        imageSpan = (TextView) findViewById(R.id.imageSpan);
        imageSpan.setTextIsSelectable(true);
    }

    private void initImageSpan() {
        String srcStr = "simple /@ image span string/@";
        Matcher matcher = Pattern.compile(targetStr).matcher(srcStr);
        SpannableString spannableString = new SpannableString(srcStr);
        while (matcher.find()) {
            spannableString.setSpan(new ImageSpan(MainActivity.this, R.mipmap.ic_launcher), matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        imageSpan.setText(spannableString);
    }

    private void initClickSpan() {
        String srcStr = "hello /@ world /@";
        hello.setMovementMethod(LinkMovementMethod.getInstance());
        SpannableString spannableString = new SpannableString(srcStr);
        Matcher matcher = Pattern.compile(targetStr).matcher(spannableString);
        while (matcher.find()) {
            spannableString.setSpan(new SimpleClickSpan(matcher.group()), matcher.start(), matcher.end(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        hello.setText(spannableString);
    }

    class SimpleClickSpan extends ClickableSpan {
        private String content;

        public SimpleClickSpan(String content) {
            this.content = content;
        }

        @Override
        public void onClick(View widget) {
            Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getResources().getColor(R.color.colorPrimary));
        }
    }
}
