package com.example.fengjian.coordinatorlayoutdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.fengjian.coordinatorlayoutdemo.R;
import com.example.fengjian.coordinatorlayoutdemo.util.DimenUtil;

/**
 * Created by fengjian on 2017/3/14.
 */

public class CircleView extends ImageView {
    private float mRadius;
    private Paint mPaint;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        if (typedArray != null) {
            mRadius = DimenUtil.dp2px(context, typedArray.getFloat(R.styleable.CircleView_radius, 0));
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int spec = MeasureSpec.makeMeasureSpec((int) mRadius, MeasureSpec.EXACTLY);
        setMeasuredDimension(spec, spec);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        drawable.draw(canvas);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
    }
}
