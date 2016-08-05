package com.jhss.pushsdk.register;

import com.tencent.android.tpush.XGPushManager;

import android.app.Application;
import android.content.Context;

/**
 * Created by pangff on 16/8/5.
 * Description XgPushRegister
 */
public class XgPushRegister {

    public static void registerPush(Context context){
        if(context instanceof Application){
            XGPushManager.registerPush(context);
        }else{
            XGPushManager.registerPush(context.getApplicationContext());
        }
    }
}
