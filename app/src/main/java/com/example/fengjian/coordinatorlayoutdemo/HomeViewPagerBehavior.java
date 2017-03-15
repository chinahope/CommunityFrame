package com.example.fengjian.coordinatorlayoutdemo;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fengjian on 2017/3/13.
 */

public class HomeViewPagerBehavior extends AppBarLayout.ScrollingViewBehavior {
    private static final String TAG = "fjdada";
    private DependViewChangedListener mDependViewChangedListener;

    public HomeViewPagerBehavior(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        Log.d(TAG, "layoutDependsOn");
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.d(TAG, "onDependentViewChanged" + "depHeight = " +dependency.getHeight() + " , depWidth = " + dependency.getWidth()  + " , depX = " + dependency.getX() + " , depY = " + dependency.getY());
        if(mDependViewChangedListener != null){
            mDependViewChangedListener.onDependentViewChanged(parent, child, dependency);
        }
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.d(TAG, "onStartNestedScroll");
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target
            , int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.d(TAG, String.format("onNestedScroll : dxConsumed = %d , dyConsumed = %d , dxUnconsumed = %d , dyUnconsumed = %d"
                , dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed));
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    public interface DependViewChangedListener{
        void onDependentViewChanged(CoordinatorLayout parent, View child, View dependency);
    }

    public void setOnDependViewChangedListener(DependViewChangedListener listener){
        this.mDependViewChangedListener = listener;
    }

    public static HomeViewPagerBehavior from(View view){
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if(layoutParams instanceof CoordinatorLayout.LayoutParams){
            CoordinatorLayout.LayoutParams params = ((CoordinatorLayout.LayoutParams) layoutParams);
            CoordinatorLayout.Behavior behavior = params.getBehavior();
            if(behavior != null && behavior instanceof HomeViewPagerBehavior){
                return ((HomeViewPagerBehavior) behavior);
            }
        }
        return null;
    }
}
