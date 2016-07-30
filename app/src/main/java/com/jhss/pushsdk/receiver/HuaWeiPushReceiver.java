package com.jhss.pushsdk.receiver;

import com.huawei.android.pushagent.PushReceiver;
import com.jhss.pushsdk.util.PushLogger;

import android.content.Context;
import android.content.Intent;

import java.io.UnsupportedEncodingException;

import static com.jhss.pushsdk.receiver.JhssPushReceiver.*;

/**
 * Created by pangff on 16/7/30.
 * Description 华为 HuaweiReceiver
 */
public class HuaWeiPushReceiver extends PushReceiver {


    @Override
    public void onToken(Context context, String s) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_THIRD_PUSH_ID, s);
        intent.setAction(ACTION_REGISTER);
        context.sendBroadcast(intent);
    }

    @Override
    public void onPushMsg(Context context, byte[] msg, String s) {
        try {
            String msgContent = new String(msg, "UTF-8");
            Intent intent = new Intent();
            intent.putExtra(EXTRA_THIRD_PUSH_ID, s);
            intent.putExtra(EXTRA_THIRD_PUSH_MSG, msgContent);
            intent.putExtra(EXTRA_THIRD_PUSH_MSG_TYPE,MSG_TYPE_PASS_THROUGH);
            intent.setAction(ACTION_MESSAGE);
            context.sendBroadcast(intent);
        } catch (UnsupportedEncodingException e) {
            PushLogger.e("HuaWeiReceiver-onPushMsg error", e);
        }
    }
}
