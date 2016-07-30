package com.jhss.pushsdk.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;

/**
 * Created by pangff on 16/7/29.
 * Description MetaUtil 获取manifest中的 meta数据
 */
public class MetaUtil {

    /**
     * 获取application下的mateData
     */
    public static String getApplicationMeta(Context context, String key)
            throws PackageManager.NameNotFoundException {

        ApplicationInfo appInfo = context.getPackageManager()
                .getApplicationInfo(context.getPackageName(),
                        PackageManager.GET_META_DATA);
        String msg = appInfo.metaData.getString(key);
        return msg;
    }


    /**
     * 获取activity meta
     */
    public static String getActivityMeta(Activity context, String key)
            throws PackageManager.NameNotFoundException {

        ActivityInfo info = context.getPackageManager()
                .getActivityInfo(context.getComponentName(),
                        PackageManager.GET_META_DATA);
        String msg = info.metaData.getString(key);
        return msg;
    }


    /**
     * 获取service meta
     */
    public static String getServiceMeta(Context context, Class serviceClass, String key)
            throws PackageManager.NameNotFoundException {

        ComponentName cn = new ComponentName(context, serviceClass);
        ServiceInfo info = context.getPackageManager()
                .getServiceInfo(cn, PackageManager.GET_META_DATA);
        String msg = info.metaData.getString(key);
        return msg;
    }


    /**
     * 获取receiver meta
     * @param context
     * @param receiverClass
     * @param key
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    public static String getReceiverMeta(Context context, Class receiverClass, String key)
            throws PackageManager.NameNotFoundException {
        ComponentName cn=new ComponentName(context, receiverClass);
        ActivityInfo info=context.getPackageManager()
                .getReceiverInfo(cn, PackageManager.GET_META_DATA);
        String msg=info.metaData.getString(key);
        return msg;
    }

}
