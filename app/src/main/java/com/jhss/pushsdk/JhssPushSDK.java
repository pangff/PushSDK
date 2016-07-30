package com.jhss.pushsdk;

import com.jhss.pushsdk.register.HuaWeiPushRegister;
import com.jhss.pushsdk.register.MiPushRegister;
import com.jhss.pushsdk.util.PlatformUtil;

import android.content.Context;

/**
 * Created by pangff on 16/7/29.
 * Description JhssPushSDK
 */
public class JhssPushSDK {

    /**
     * 注册推送服务
     * @param context
     */
    public static void registerPush(Context context){
        if(PlatformUtil.isMIUI(context)){
            MiPushRegister.registerPush(context);
        }else if(PlatformUtil.isHuaWei(context)){
            HuaWeiPushRegister.registerPush(context);
        }else{
            //默认小米
            MiPushRegister.registerPush(context);
        }
    }

}
