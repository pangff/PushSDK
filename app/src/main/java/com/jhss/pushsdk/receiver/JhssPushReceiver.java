package com.jhss.pushsdk.receiver;

import com.jhss.pushsdk.util.PushLogger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by pangff on 16/7/30.
 * Description JhssPushReceiver
 */
public class JhssPushReceiver extends BroadcastReceiver{

    /**
     * receiver监听的msg
     */
    protected static final String ACTION_REGISTER = "com.jhss.action.push.REGISTER";
    protected static final String ACTION_MESSAGE = "com.jhss.action.push.MESSAGE";

    /**
     * 消息传递key
     */
    protected static final String EXTRA_THIRD_PUSH_ID = "extra_third_push_id";
    protected static final String EXTRA_THIRD_PUSH_MSG = "extra_third_push_msg";
    protected static final String EXTRA_THIRD_PUSH_MSG_TYPE = "extra_third_push_msg_type";


    /**
     * 消息类型
     */
    protected static final String MSG_TYPE_PASS_THROUGH = "msg_type_pass_through";
    protected static final String MSG_TYPE_NOTICE = "msg_type_notice";
    protected static final String MSG_TYPE_NOTICE_CLICKED = "msg_type_notice_clicked";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction()==ACTION_REGISTER){
            String thirdPushId = intent.getStringExtra(EXTRA_THIRD_PUSH_ID);
            onReceiveRegisterResult(context,thirdPushId);
        }
        if(intent.getAction()==ACTION_MESSAGE){
            String msg = intent.getStringExtra(EXTRA_THIRD_PUSH_MSG);
            String msgType = intent.getStringExtra(EXTRA_THIRD_PUSH_MSG_TYPE);
            switch (msgType){
                case MSG_TYPE_PASS_THROUGH:
                    onReceivePassThroughMessage(context,msg);
                    break;
                case MSG_TYPE_NOTICE:
                    onNotificationMessageArrived(context,msg);
                    break;
                case MSG_TYPE_NOTICE_CLICKED:
                    onNotificationMessageClicked(context,msg);
                    break;
                default:{
                    onReceivePassThroughMessage(context,msg);
                }
            }
        }
    }

    /**
     * 服务注册
     * @param context
     * @param thirdPushId
     */
    public void onReceiveRegisterResult(Context context,String thirdPushId){

        PushLogger.d("JhssPushReceiver-onReceiveRegisterResult:thirdPushId="+thirdPushId);
    }

    /**
     * 通知消息。
     */
    public void onNotificationMessageArrived(Context context, String message) {
        PushLogger.d("JhssPushReceiver-onNotificationMessageArrived:message="+message);
    }

    /**
     * 通知点击后消息
     */
    public void onNotificationMessageClicked(Context context, String message) {
        PushLogger.d("JhssPushReceiver-onNotificationMessageClicked:message="+message);
    }

    /**
     * 透传消息
     */
    public void onReceivePassThroughMessage(Context context, String message) {

        PushLogger.d("JhssPushReceiver-onReceivePassThroughMessage:message="+message);
    }


}
