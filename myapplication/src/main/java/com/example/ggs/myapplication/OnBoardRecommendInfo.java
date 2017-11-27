package com.example.ggs.myapplication;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengjian on 2017/11/27.
 */

public class OnBoardRecommendInfo {
    public List<OnBoardRecommendModel> allData = new ArrayList<OnBoardRecommendModel>();

    public static class OnBoardRecommendModel {
        public boolean isOverNumber = false;
        public String cname = "";
        public String ename = "";
        public String img = "";
        public String is_default = "";

        public boolean isSelected() {
            return "1".endsWith(is_default);
        }

        public void setSelected(boolean isSelected) {
            is_default = isSelected ? "1" : "0";
        }

        public boolean isValid() {
            return !TextUtils.isEmpty(cname) && !TextUtils.isEmpty(ename);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof OnBoardRecommendInfo) {
            OnBoardRecommendInfo dst = (OnBoardRecommendInfo) o;
            if (allData.size() != dst.allData.size()) {
                return false;
            }
            for (int i = 0; i < allData.size(); i++) {
                if (!allData.get(i).ename.equals(dst.allData.get(i).ename)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
