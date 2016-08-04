package com.jhss.pushsdk.receiver;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.huawei.android.pushagent.api.PushEventReceiver;
import com.jhss.pushsdk.util.PushLogger;

import java.io.UnsupportedEncodingException;

import static com.jhss.pushsdk.receiver.JhssPushReceiver.*;
;
/**
 * Created by pangff on 16/7/30.
 * Description 华为 HuaweiReceiver
 */
public class HuaWeiPushReceiver extends PushEventReceiver {
    /**
     * token申请成功后会自动回调此方法
     * @param context
     * @param s
     */
    @Override
    public void onToken(Context context, String s) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_THIRD_PUSH_ID, s);
        intent.setAction(ACTION_REGISTER);
        context.sendBroadcast(intent);
        PushLogger.d("获取token成功"+s);
    }

    /**
     * 推送消息下来自动回调
     * @param context
     * @param bytes
     * @param bundle
     * @return
     */
    @Override
    public boolean onPushMsg(Context context, byte[] bytes, Bundle bundle) {
        try {
            PushLogger.d("推送消息下来自动回调");
            String msgContent = new String(bytes, "UTF-8");
            Intent intent = new Intent();
            intent.putExtra(EXTRA_THIRD_PUSH_ID, bundle);
            intent.putExtra(EXTRA_THIRD_PUSH_MSG, msgContent);
            intent.putExtra(EXTRA_THIRD_PUSH_MSG_TYPE,MSG_TYPE_PASS_THROUGH);
            intent.setAction(ACTION_MESSAGE);
            context.sendBroadcast(intent);
        } catch (UnsupportedEncodingException e) {
            PushLogger.e("HuaWeiReceiver-onPushMsg error", e);
        }

        return false;

    }

    /**
     * 实现业务事件的回调
     * @param context
     * @param event
     * @param bundle
     */
    @Override
    public void onEvent(Context context, Event event, Bundle bundle) {
        super.onEvent(context, event, bundle);
        PushLogger.d("success");
        if (Event.NOTIFICATION_OPENED.equals(event) || Event.NOTIFICATION_CLICK_BTN.equals(event)) {
            int notifyId = bundle.getInt(BOUND_KEY.pushNotifyId, 0);
            if (0 != notifyId) {
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(notifyId);
            }
            String content = "收到通知附加消息： " + bundle.getString(BOUND_KEY.pushMsgKey);
            Intent intent = new Intent();
            intent.putExtra(EXTRA_THIRD_PUSH_MSG,content);                      //内容
            intent.putExtra(EXTRA_THIRD_PUSH_MSG_TYPE,MSG_TYPE_NOTICE_CLICKED);//类型
            intent.setAction(ACTION_MESSAGE);
            context.sendBroadcast(intent);
        }


    }
}
