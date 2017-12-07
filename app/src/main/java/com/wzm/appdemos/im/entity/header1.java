package com.wzm.appdemos.im.entity;

import android.content.Context;
import android.content.Intent;

import com.wzm.appdemos.im.service.PullMsgReceiver;
import com.wzm.appdemos.im.utils.Protocol;

/**
 * 类名：com.wzm.appdemos.im.entity
 * 时间：2017/11/27 20:20
 * 描述：发送消息
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author wangzm
 */

public class header1 {
    public void sendReceiver(Context c, Protocol protocol){
        Intent intent =new Intent();
        intent.setAction(PullMsgReceiver.CHAT_TEXT_ACTION);
        intent.putExtra("msg",protocol);
        c.sendBroadcast(intent);
    }
}
