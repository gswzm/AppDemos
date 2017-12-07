package com.wzm.appdemos.im.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 类名：com.wzm.appdemos.im.service
 * 时间：2017/11/27 20:01
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author wangzm
 */

public class PullMsgReceiver extends BroadcastReceiver{
    public static final String DUANXIAN_ACTION="com.fht.duanxian";
    public static final String CHAT_TEXT_ACTION="com.fht.chat";
    public static final String HEARTACTION="com.fht.heart";

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
