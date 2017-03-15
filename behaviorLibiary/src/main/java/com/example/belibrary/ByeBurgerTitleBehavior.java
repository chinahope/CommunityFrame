package com.example.belibrary;

/**
 * Created by wing on 11/5/16.
 */

import android.content.Context;
import android.nfc.Tag;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Bye Bye Burger Android Title Bar Behavior
 *
 * Created by wing on 11/4/16.
 */

public class ByeBurgerTitleBehavior extends ByeBurgerBehavior {
  private final static String TAG = "fjdada";
  public ByeBurgerTitleBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
    Log.e(TAG, "onNestedPreScroll \n dx = " + dx + " , dy = " + dy + " , consumed  = " + consumed.toString());
    super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
  }

  @Override
  public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
    return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
  }

  @Override
  public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    Log.e(TAG,"onNestedScroll \n dxConsumed = " + dxConsumed + " , dyConsumed = " + dyConsumed + " , dxUnconsumed = " + dxUnconsumed + " , dyUnconsumed = " + dyUnconsumed);
    super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
  }

  @Override public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
    if (canInit) {
      mAnimateHelper = TranslateAnimateHelper.get(child);
      canInit = false;
    }
    return super.layoutDependsOn(parent, child, dependency);
  }


  @Override protected void onNestPreScrollInit(View child) {
    if (isFirstMove) {
      isFirstMove = false;
      mAnimateHelper.setStartY(child.getY());
      mAnimateHelper.setMode(TranslateAnimateHelper.MODE_TITLE);
    }
  }
}

