package com.example.ggs.recyclerviewdivider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by fengjian on 2017/4/17.
 */

public class HorzItemDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;
    private int mOrientation;
    private Drawable mDivider;
    private int mWidth;

    public HorzItemDecoration(Context context, int resID, int width, int orientation) {
        mDivider = context.getResources().getDrawable(resID);
        mWidth = width;
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == HORIZONTAL) {
            drawHorz(c, parent, state);
        } else if (mOrientation == VERTICAL) {
            drawVerl(c, parent, state);
        } else {
            throw new RuntimeException("invalid orientation");
        }
    }

    private void drawHorz(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int top = child.getTop() - params.topMargin;
            int right = left + mWidth;
            int bottom = child.getBottom() + params.bottomMargin;
            if (mWidth > mDivider.getIntrinsicWidth()) {
                left = (right - left) / 2 + left - mDivider.getIntrinsicWidth() / 2;
                right = left + mDivider.getIntrinsicWidth();
            }
            if (mDivider.getIntrinsicHeight() < bottom - top) {
                top = (bottom - top) / 2 + top - mDivider.getIntrinsicHeight() / 2;
                bottom = top + mDivider.getIntrinsicHeight();
            }
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawVerl(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft() - params.leftMargin;
            int top = child.getBottom() + params.bottomMargin;
            int right = child.getRight() + params.rightMargin;
            int bottom = right + mDivider.getIntrinsicHeight();
            if (mWidth > mDivider.getIntrinsicHeight()) {
                top = (bottom - top) / 2 + top - mDivider.getIntrinsicHeight() / 2;
                bottom = top + mDivider.getIntrinsicHeight();
            }
            if (mDivider.getIntrinsicWidth() < right - left) {
                left = (right - left) / 2 + left - mDivider.getIntrinsicWidth() / 2;
                right = left + mDivider.getIntrinsicWidth();
            }
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOrientation == HORIZONTAL) {
            outRect.right = mWidth;
        } else {
            outRect.bottom = mWidth;
        }
    }
}
