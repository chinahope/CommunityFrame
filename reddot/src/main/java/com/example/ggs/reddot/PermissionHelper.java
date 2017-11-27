package com.example.ggs.reddot;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by fengjian on 2017/9/13.
 */

public class PermissionHelper {
    public static boolean setMode(Context context) {
        boolean bRet = false;
        Object object = context.getSystemService(Context.APP_OPS_SERVICE);
        if (object == null) {
            System.out.println("fjdada null ");
            return false;
        }
        AppOpsManager opsManager = (AppOpsManager) object;
        try {
            Method methodSetMode = AppOpsManager.class.getDeclaredMethod("setMode", new Class[]{int.class, int.class, String.class, int.class});
            methodSetMode.setAccessible(true);
            methodSetMode.invoke(opsManager, new Object[]{0, Process.myUid(), context.getPackageName(), AppOpsManager.MODE_ALLOWED});
            bRet = true;
            System.out.println("fjdada success");
        } catch (Exception e) {
            System.out.println("fjdada e : " + e.toString());
            e.printStackTrace();
        }
        return bRet;
    }
}
