package com.jhss.pushsdk.receiver;

import com.jhss.pushsdk.util.PushLogger;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import static com.jhss.pushsdk.receiver.JhssPushReceiver.ACTION_MESSAGE;
import static com.jhss.pushsdk.receiver.JhssPushReceiver.ACTION_REGISTER;
import static com.jhss.pushsdk.receiver.JhssPushReceiver.EXTRA_THIRD_PUSH_ID;
import static com.jhss.pushsdk.receiver.JhssPushReceiver.EXTRA_THIRD_PUSH_MSG;
import static com.jhss.pushsdk.receiver.JhssPushReceiver.EXTRA_THIRD_PUSH_MSG_TYPE;
import static com.jhss.pushsdk.receiver.JhssPushReceiver.MSG_TYPE_NOTICE;
import static com.jhss.pushsdk.receiver.JhssPushReceiver.MSG_TYPE_NOTICE_CLICKED;
import static com.jhss.pushsdk.receiver.JhssPushReceiver.MSG_TYPE_PASS_THROUGH;

/**
 * Created by pangff on 16/8/5.
 * Description XgPushReceiver
 */
public class XgPushReceiver extends XGPushBaseReceiver{

    @Override
    public void onRegisterResult(Context context, int i,
            XGPushRegisterResult xgPushRegisterResult) {
        PushLogger.d("信鸽注册回调");
        Intent intent = new Intent();
        intent.putExtra(EXTRA_THIRD_PUSH_ID, xgPushRegisterResult.getToken());
        intent.setAction(ACTION_REGISTER);
        context.sendBroadcast(intent);
    }

    @Override
    public void onUnregisterResult(Context context, int i) {

    }

    @Override
    public void onSetTagResult(Context context, int i, String s) {

    }

    @Override
    public void onDeleteTagResult(Context context, int i, String s) {

    }

    @Override
    public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {
        if (xgPushTextMessage != null && !TextUtils.isEmpty(xgPushTextMessage.getContent())) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_THIRD_PUSH_MSG, xgPushTextMessage.getContent());
            intent.putExtra(EXTRA_THIRD_PUSH_MSG_TYPE, MSG_TYPE_PASS_THROUGH);
            intent.setAction(ACTION_MESSAGE);
            context.sendBroadcast(intent);
        }else{
            PushLogger.e("XgPushReceiver-onTextMessage-empty-msg");
        }
    }

    @Override
    public void onNotifactionClickedResult(Context context,
            XGPushClickedResult xgPushClickedResult) {
        if (xgPushClickedResult != null && !TextUtils.isEmpty(xgPushClickedResult.getContent())) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_THIRD_PUSH_MSG, xgPushClickedResult.getContent());
            intent.putExtra(EXTRA_THIRD_PUSH_MSG_TYPE, MSG_TYPE_NOTICE_CLICKED);
            intent.setAction(ACTION_MESSAGE);
            context.sendBroadcast(intent);
        }else{
            PushLogger.e("MiPushXGPushClickedResultReceiver-onNotifactionClickedResult-empty-msg");
        }
    }

    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {
        if (xgPushShowedResult != null && !TextUtils.isEmpty(xgPushShowedResult.getContent())) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_THIRD_PUSH_MSG, xgPushShowedResult.getContent());
            intent.putExtra(EXTRA_THIRD_PUSH_MSG_TYPE, MSG_TYPE_NOTICE);
            intent.setAction(ACTION_MESSAGE);
            context.sendBroadcast(intent);
        }else{
            PushLogger.e("xgPushShowedResult-onNotifactionShowedResult-empty-msg");
        }
    }
}
