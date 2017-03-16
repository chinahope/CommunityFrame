package com.example.fengjian.coordinatorlayoutdemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.fengjian.coordinatorlayoutdemo.util.DimenUtil;
import com.example.fengjian.coordinatorlayoutdemo.view.SimpleViewPager;

/**
 * Created by fengjian on 2017/3/15.
 */

public class BottomScaleBehavior extends CoordinatorLayout.Behavior<View> {
    private float mFloatHeight = 100f;

    private static final String TAG = "BottomScaleBehavior";

    public BottomScaleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof SimpleViewPager;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    /**
     * @param coordinatorLayout
     * @param child             绑定当前behavior的view
     * @param target            可滑动的view
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        float dp = DimenUtil.dp2px(target.getContext(), dy);
        Log.d(TAG, "onNestedPreScroll dx = " + dx + " ,dy = " + dy);
        if (Math.abs(dy) > 2) {
            float oldScale = child.getScaleX();
            float curScale = 0;
            if (dy > 0) {
                //to up decrease scale
                curScale = Math.max(oldScale - dp / mFloatHeight, 0);
                child.setScaleX(curScale);
                child.setScaleY(curScale);
            } else {
                //to wown increase scale
                curScale = Math.min(oldScale - dp / mFloatHeight, 1);
                child.setScaleX(curScale);
                child.setScaleY(curScale);
            }
        }
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    public static BottomScaleBehavior from(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.LayoutParams params = ((CoordinatorLayout.LayoutParams) layoutParams);
            CoordinatorLayout.Behavior behavior = params.getBehavior();
            if (behavior != null && behavior instanceof BottomScaleBehavior) {
                return ((BottomScaleBehavior) behavior);
            }
        }
        return null;
    }
}
