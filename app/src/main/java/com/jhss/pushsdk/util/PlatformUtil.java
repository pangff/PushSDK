package com.jhss.pushsdk.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by pangff on 16/7/29.
 * Description PlatformUtil 平台判断util
 */
public class PlatformUtil {

    private static final String TAG = "PlatformUtil";


    private static final String phoneBrand = Build.BRAND;

    /**
     * 判断是否魅族
     * @return
     */
    public static boolean isFlyme() {
        try {
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        } catch (final Exception e) {
            return false;
        }
    }

    /**
     * 判断华为推送是否可用
     * @param context
     * @return
     */
    public static boolean isHuaWei(Context context) {
        try {
            if ((phoneBrand.equalsIgnoreCase("huawei"))
                    || (phoneBrand.equalsIgnoreCase("honor"))) {
                return true;
            }
            Log.d(TAG, "HuaWeiPushRegister checkDevice flag=false");
            return false;
        } catch (Throwable e) {
            Log.d(TAG, "HuaWeiPushRegister checkDevice flag=false");
        }
        return false;
    }


    /**
     * 判断小米推送是否可用
     */
    public static boolean isMIUI(Context context) {
        String packageXiaomi = "com.xiaomi.xmsf";
        String xiaomi = "Xiaomi".toLowerCase();
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo pkgInfo = null;
            if (TextUtils.equals(xiaomi, phoneBrand.toLowerCase())) {
                pkgInfo = manager.getPackageInfo(packageXiaomi, PackageManager.GET_SERVICES);
                if ((pkgInfo != null) && (pkgInfo.versionCode >= 105)) {
                    Log.e(TAG, "MiPushRegistar checkDevice flag=true");
                    return true;
                }
                Log.e(TAG, "MiPushRegistar checkDevice flag=false");
                return false;
            }
            Log.e(TAG, "MiPushRegistar checkDevice flag=false");
            return false;
        } catch (Throwable e) {
            Log.e(TAG, "MiPushRegistar checkDevice error=" + e);
        }
        return false;
    }

}
