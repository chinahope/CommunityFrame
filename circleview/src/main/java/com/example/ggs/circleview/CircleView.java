package com.example.ggs.circleview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by fengjian on 2017/3/30.
 */

public class CircleView extends ImageView {
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND_RECT = 1;
    private float mRadius;
    private int mType;
    private float mStrokeWidth;
    private Paint mPaint;
    private Paint mStrokePaint;
    private int mStrokeColor;
    private int mWidth;
    private int mHeight;
    private BitmapShader mShader;
    private float[] centerPoint = new float[2];
    Matrix matrix = new Matrix();

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mRadius = typedArray.getDimensionPixelOffset(R.styleable.CircleView_radius, 10);
        mType = typedArray.getInt(R.styleable.CircleView_tyle, TYPE_CIRCLE);
        mStrokeWidth = typedArray.getDimensionPixelOffset(R.styleable.CircleView_stroke_width, 2);
        mStrokeColor = typedArray.getColor(R.styleable.CircleView_stroke_color, Color.BLACK);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(mStrokeWidth);
        mStrokePaint.setColor(mStrokeColor);
        setup();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        centerPoint[0] = w / 2;
        centerPoint[1] = h / 2;
        setup();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerPoint[0] = getMeasuredWidth() / 2;
        centerPoint[1] = getMeasuredHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        canvas.drawCircle(centerPoint[0], centerPoint[1], mRadius, mPaint);
        canvas.drawCircle(centerPoint[0], centerPoint[1], mRadius, mStrokePaint);
    }

    @Override
    public void setImageResource(int resId) {
        setup();
        super.setImageResource(resId);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        setup();
        super.setImageBitmap(bm);
    }

    private void setup() {
        if (getDrawable() == null) {
            return;
        }
        matrix.set(null);
        Bitmap bitmap = getBitmap(getDrawable());
        int bWidth = bitmap.getWidth();
        int bHeight = bitmap.getHeight();
        float scale = 1;
        float hScale = 1;
        float wScale = 1;
        if (bWidth > bHeight) {
            //scale height
            scale = 2 * mRadius / bHeight;
        } else {
            scale = 2 * mRadius / bWidth;
        }
        matrix.setScale(scale, scale);
        float dx = centerPoint[0] - mRadius;
        float dy = centerPoint[1] - mRadius;
        matrix.postTranslate(dx, dy);
        mShader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        mShader.setLocalMatrix(matrix);
        mPaint.setShader(mShader);
        invalidate();
    }

    private Bitmap getBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
        return bitmap;
    }
}
