package com.wzm.appdemos.im.utils;


import com.google.gson.Gson;

import java.util.UUID;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
public class SetRequestMessage {
    public static byte[] sendMessage(String msg) throws Exception {

        byte[] _msg=msg.getBytes("UTF-8");

        return _msg;
    }
    public static Protocol encodeMsg(byte[] b) throws Exception {
        String result=new String(b);
        Protocol protocol=new Gson().fromJson(result,Protocol.class);
        return protocol;
    }
    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     * @return
     */
    public static String getUUID() {

        return UUID.randomUUID().toString().replace("-", "");
    }
}
