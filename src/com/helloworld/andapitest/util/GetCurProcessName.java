package com.helloworld.andapitest.util;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by babycomingin100days on 2017/3/20.
 */
public class GetCurProcessName {
   public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }
}
