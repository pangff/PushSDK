package com.jhss.pushsdk.util;

import android.util.Log;

/**
 * Created by pangff on 16/7/30.
 * Description PushLogger
 */
public class PushLogger {

    private static final String TAG = "JHSS-PUSH";

    public static void e(String log){
        Log.e(TAG,log);
    }

    public static void e(String log,Throwable e){
        Log.e(TAG,log,e);
    }

    public static void d(String log){
        Log.d(TAG,log);
    }
}
