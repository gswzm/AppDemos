package com.wzm.appdemos.im.socket;

import android.util.Log;

import com.google.gson.Gson;
import com.wzm.appdemos.im.utils.IMCache;
import com.wzm.appdemos.im.utils.IMConstants;
import com.wzm.appdemos.im.utils.Protocol;
import com.wzm.appdemos.im.utils.SetRequestMessage;

import java.util.Map;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class IMClient {

    public static ByteBuf packingMsg(Protocol protocol) throws Exception {
//        String body=JsonHelper.mapToJson1(map);
        String body=new Gson().toJson(protocol);
        Log.d("send data:",body);
        return Unpooled.copiedBuffer(SetRequestMessage.sendMessage(
                body));
    }

    public static void sendData(Protocol protocol){
        try {
            if(IMCache.isConnected){
                protocol.setDevice("2");
                ByteBuf data = packingMsg(protocol);
                ImBootstrap.instance().channel().writeAndFlush(data);
            }else{
                Log.e("tcp channel:","管道已关闭");
                //TODO 重连
            }
        }catch(Exception e){
            e.getMessage();
            Log.e("=======================",e.getStackTrace()[0].getLineNumber()+"");
            Log.e("=====================",e.getStackTrace()[0].getClassName()+"");
            Log.e("send error message:",e.getMessage()==null?"":e.getMessage());
        }
    }

    public static void sendData1(){
        try {
            Protocol protocol=new Protocol();
            protocol.setHeader("0");
            protocol.setRoomId("110");
            protocol.setUserId("123456");
            protocol.setDevice("2");
            String body=new Gson().toJson(protocol);
            ByteBuf data = Unpooled.copiedBuffer(SetRequestMessage.sendMessage(body));
            ImBootstrap.instance().channel().writeAndFlush(data);
        }catch(Exception e){
            e.getMessage();
            Log.e("send error message:",e.getMessage());
        }
    }
}
