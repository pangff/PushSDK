package com.jhss.pushsdk.register;

import android.content.Context;

import com.huawei.android.pushagent.api.PushManager;

/**
 * Created by pangff on 16/7/30.
 * Description HuaWeiPushRegister
 */
public class HuaWeiPushRegister {
    public static void registerPush(Context context) {
        // 获取客户端AccessToken,获取之前请先确定该应用（包名）已经在开发者联盟上创建成功，并申请、审核通过Push权益
        // 该测试应用已经注册过
        PushManager.requestToken(context);
    }
}
