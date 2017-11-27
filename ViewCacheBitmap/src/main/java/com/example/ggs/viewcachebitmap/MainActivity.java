package com.example.ggs.viewcachebitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        TextView helloView = new TextView(this);
        helloView.setBackgroundResource(R.mipmap.ic_launcher);
        ImageView cacheView = (ImageView) findViewById(R.id.iv_cache);
        Bitmap viewBitmap2 = getViewBitmap2(helloView);
        if(viewBitmap2 == null){
            viewBitmap2 = getViewBitmap(helloView);
        }
//        if(viewBitmap2 == null){
//            viewBitmap2 = convertViewToBitmap(helloView, 200 , 100);
//        }
        if(viewBitmap2 == null){
            viewBitmap2 = convertViewToBitmap(helloView);
        }
        cacheView.setImageBitmap(viewBitmap2);
    }

    public static Bitmap getViewBitmap2(View view) {
        view.setDrawingCacheEnabled(true);
        view.invalidate();
        view.invalidate();
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    public static Bitmap getViewBitmap(View view) {
        boolean willNotCache = view.willNotCacheDrawing();
        view.setWillNotCacheDrawing(false);
        int color = view.getDrawingCacheBackgroundColor();
        view.setDrawingCacheBackgroundColor(0);

        if (color != 0) {
            view.destroyDrawingCache();
        }
        view.buildDrawingCache();
        Bitmap cacheBitmap = view.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        view.destroyDrawingCache();
        view.setWillNotCacheDrawing(willNotCache);
        view.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }

    public static Bitmap convertViewToBitmap(View view, int bitmapWidth, int bitmapHeight){
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));

        return bitmap;
    }

    public static Bitmap convertViewToBitmap(View view){
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }
}
