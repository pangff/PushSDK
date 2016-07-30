package com.jhss.pushsdk.register;

import com.jhss.pushsdk.util.MetaUtil;
import com.xiaomi.mipush.sdk.MiPushClient;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by pangff on 16/7/30.
 * Description MiPushRegister
 */
public class MiPushRegister  {

    public static void registerPush(Context context) {
        String APP_ID = null;
        String APP_KEY = null;
        try {
            APP_ID = MetaUtil.getApplicationMeta(context,"MIUI_APP_ID").substring(1);
            APP_KEY = MetaUtil.getApplicationMeta(context,"MIUI_APP_KEY").substring(1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if(context instanceof Application){
            MiPushClient.registerPush(context, APP_ID, APP_KEY);
        }else{
            MiPushClient.registerPush(context.getApplicationContext(), APP_ID, APP_KEY);
        }
    }
}
