package com.jhss.pushsdk.receiver;

import com.jhss.pushsdk.util.PushLogger;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import java.util.List;

import static com.jhss.pushsdk.receiver.JhssPushReceiver.ACTION_MESSAGE;
import static com.jhss.pushsdk.receiver.JhssPushReceiver.EXTRA_THIRD_PUSH_MSG;
import static com.jhss.pushsdk.receiver.JhssPushReceiver.EXTRA_THIRD_PUSH_MSG_TYPE;
import static com.jhss.pushsdk.receiver.JhssPushReceiver.MSG_TYPE_NOTICE;
import static com.jhss.pushsdk.receiver.JhssPushReceiver.MSG_TYPE_NOTICE_CLICKED;
import static com.jhss.pushsdk.receiver.JhssPushReceiver.MSG_TYPE_PASS_THROUGH;

/**
 * Created by pangff on 16/7/30.
 * Description 小米MiReceiver
 */
public class MiPushReceiver extends PushMessageReceiver {


    @Override
    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {

        String command = miPushCommandMessage.getCommand();
        List<String> arguments = miPushCommandMessage.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                Intent intent = new Intent();
                intent.putExtra(JhssPushReceiver.EXTRA_THIRD_PUSH_ID, cmdArg1);
                intent.setAction(JhssPushReceiver.ACTION_REGISTER);
                context.sendBroadcast(intent);
            } else {
                PushLogger.e("MiPushReceiver:" + miPushCommandMessage.getResultCode() + "reason:"
                        + miPushCommandMessage.getReason());
            }
        } else if (MiPushClient.COMMAND_SET_ALIAS.equals(command)) {
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                PushLogger.d(
                        "MiPushReceiver-COMMAND_SET_ALIAS_SUCCESS:" + cmdArg1);
            } else {
                PushLogger.e(
                        "MiPushReceiver-COMMAND_SET_ALIAS_ERROR:" + miPushCommandMessage
                                .getResultCode() + "reason:"
                                + miPushCommandMessage.getReason());
            }
        } else if (MiPushClient.COMMAND_UNSET_ALIAS.equals(command)) {
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                PushLogger.d(
                        "MiPushReceiver-COMMAND_UNSET_ALIAS_SUCCESS:" + cmdArg1);
            } else {
                PushLogger.e(
                        "MiPushReceiver-COMMAND_UNSET_ALIAS_ERROR:" + miPushCommandMessage
                                .getResultCode() + "reason:"
                                + miPushCommandMessage.getReason());
            }
        } else if (MiPushClient.COMMAND_SET_ACCOUNT.equals(command)) {
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                PushLogger.d(
                        "MiPushReceiver-COMMAND_SET_ACCOUNT_SUCCESS:" + cmdArg1);
            } else {
                PushLogger.e(
                        "MiPushReceiver-COMMAND_SET_ACCOUNT_ERROR:" + miPushCommandMessage
                                .getResultCode() + "reason:"
                                + miPushCommandMessage.getReason());
            }
        } else if (MiPushClient.COMMAND_UNSET_ACCOUNT.equals(command)) {
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                PushLogger.d(
                        "MiPushReceiver-COMMAND_UNSET_ACCOUNT_SUCCESS:" + cmdArg1);
            } else {
                PushLogger.e(
                        "MiPushReceiver-COMMAND_UNSET_ACCOUNT_ERROR:" + miPushCommandMessage
                                .getResultCode() + "reason:"
                                + miPushCommandMessage.getReason());
            }
        } else if (MiPushClient.COMMAND_SUBSCRIBE_TOPIC.equals(command)) {
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                PushLogger.d(
                        "MiPushReceiver-COMMAND_SUBSCRIBE_TOPIC_SUCCESS:" + cmdArg1);
            } else {
                PushLogger.e(
                        "MiPushReceiver-COMMAND_SUBSCRIBE_TOPIC_ERROR:" + miPushCommandMessage
                                .getResultCode() + "reason:"
                                + miPushCommandMessage.getReason());
            }
        } else if (MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC.equals(command)) {
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                PushLogger.d(
                        "MiPushReceiver-COMMAND_UNSUBSCRIBE_TOPIC_SUCCESS:" + cmdArg1);
            } else {
                PushLogger.e(
                        "MiPushReceiver-COMMAND_UNSUBSCRIBE_TOPIC_ERROR:" + miPushCommandMessage
                                .getResultCode() + "reason:"
                                + miPushCommandMessage.getReason());
            }
        } else if (MiPushClient.COMMAND_SET_ACCEPT_TIME.equals(command)) {
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                PushLogger.d(
                        "MiPushReceiver-COMMAND_SET_ACCEPT_TIME_SUCCESS:mStartTime=" + cmdArg1
                                + ";mEndTime=" + cmdArg2);
            } else {
                PushLogger.e(
                        "MiPushReceiver-COMMAND_SET_ACCEPT_TIME_ERROR:" + miPushCommandMessage
                                .getResultCode() + "reason:"
                                + miPushCommandMessage.getReason());
            }
        } else {
            PushLogger.e(
                    "MiPushReceiver-COMMAND_NO_FOUND:-reason:" + miPushCommandMessage.getReason());
        }
    }

    /**
     * 通知消息。
     * 非MIUI都可以收到MIUI 7 之后才可以收到
     */
    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        if (miPushMessage != null && !TextUtils.isEmpty(miPushMessage.getContent())) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_THIRD_PUSH_MSG, miPushMessage.getContent());
            intent.putExtra(EXTRA_THIRD_PUSH_MSG_TYPE, MSG_TYPE_NOTICE);
            intent.setAction(ACTION_MESSAGE);
            context.sendBroadcast(intent);
        }else{
            PushLogger.e("MiPushReceiver-onNotificationMessageArrived-empty-msg");
        }
    }

    /**
     * 通知点击后消息
     */
    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        if (miPushMessage != null && !TextUtils.isEmpty(miPushMessage.getContent())) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_THIRD_PUSH_MSG, miPushMessage.getContent());
            intent.putExtra(EXTRA_THIRD_PUSH_MSG_TYPE, MSG_TYPE_NOTICE_CLICKED);
            intent.setAction(ACTION_MESSAGE);
            context.sendBroadcast(intent);
        }else{
            PushLogger.e("MiPushReceiver-onNotificationMessageClicked-empty-msg");
        }
    }

    /**
     * 透传消息
     */
    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        if (miPushMessage != null && !TextUtils.isEmpty(miPushMessage.getContent())) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_THIRD_PUSH_MSG, miPushMessage.getContent());
            intent.putExtra(EXTRA_THIRD_PUSH_MSG_TYPE, MSG_TYPE_PASS_THROUGH);
            intent.setAction(ACTION_MESSAGE);
            context.sendBroadcast(intent);
        }else{
            PushLogger.e("MiPushReceiver-onReceivePassThroughMessage-empty-msg");
        }

    }
}
