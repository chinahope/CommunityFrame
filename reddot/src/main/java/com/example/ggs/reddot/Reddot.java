package com.example.ggs.reddot;

import android.graphics.Color;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengjian on 2017/5/31.
 */

public class Reddot {
    private WeakReference<View> mView;
    private Reddot mParent;
    private List<Reddot> mChildList;
    private int mVisible = View.GONE;

    public Reddot(View view) {
        mView = new WeakReference<View>(view);
        mChildList = new ArrayList<>(2);
    }

    public void addChild(Reddot child) {
        child.mParent = this;
        mChildList.add(child);
    }

    public void change(int visible) {
        if (getChildCount() == 0) {
            toggleViewVisible(visible);
            if (mParent != null) {
                mParent.change(visible);
            }
        } else {
            int v = getVisible(calcVisible());
            if (mVisible != v) {
                toggleViewVisible(v);
                if (mParent != null) {
                    mParent.change(mVisible);
                }
            }
        }
    }

    private boolean calcVisible() {
        boolean isVisible = false;
        for (int i = 0; i < mChildList.size(); i++) {
            Reddot r = mChildList.get(i);
            if (r.isVisible()) {
                isVisible = true;
                break;
            }
        }
        return isVisible;
    }

    private void toggleViewVisible(int visible) {
        mVisible = visible;
        View view = mView.get();
        if (view != null) {
//            view.setVisibility(isVisible() ? View.VISIBLE : View.GONE);
            view.setBackgroundColor(isVisible() ? Color.WHITE : Color.parseColor("#aaaaaa"));
        }
    }

    public void removeChild(Reddot child) {
        mChildList.remove(child);
    }

    public int getVisible(boolean isVisible) {
        return isVisible ? View.VISIBLE : View.GONE;
    }

    public boolean isVisible() {
        return mVisible == View.VISIBLE;
    }

    public int getChildCount() {
        return mChildList.size();
    }
}
