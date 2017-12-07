package com.wzm.appdemos.im.service;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.Gson;
import com.wzm.appdemos.im.socket.ImBootstrap;
import com.wzm.appdemos.im.utils.ClassMap;
import com.wzm.appdemos.im.utils.IMCache;
import com.wzm.appdemos.im.utils.Protocol;
import com.wzm.im.imbean.EMMessage;

import java.lang.reflect.Method;
import java.util.List;


/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class IMHelper {
    private static final String TAG="IMHelper";
    private ImBootstrap ibs;

    /**
     * 单例模式
     */
    public  static IMHelper instance;
    private IMHelper() {
    }

    public synchronized static IMHelper getInstance() {
        if (instance == null) {
            instance = new IMHelper();
        }
        return instance;
    }
    private Context appContext;
    /**
     * init helper
     *
     * @param context
     *            application context
     */
    public void init(Context context) {
        appContext = context;
        sendResponseMsg();
        initReceiver();
        initReceiver1();
    }
    private PullMsgReceiver pullMsgReceiver;
    private void initReceiver(){
        pullMsgReceiver=new PullMsgReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                String msg=intent.getStringExtra("msg");

            }
        };
        appContext.registerReceiver(pullMsgReceiver,new IntentFilter(PullMsgReceiver.CHAT_TEXT_ACTION));
    }
    private void initReceiver1(){
        pullMsgReceiver=new PullMsgReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                if(!IMCache.isConnected){
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            try {
                                ImBootstrap.instance().doConnect();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }

            }
        };
        appContext.registerReceiver(pullMsgReceiver,new IntentFilter(PullMsgReceiver.DUANXIAN_ACTION));
    }
    /**
     *接收数据
     */
    public void sendResponseMsg(){
        try{
            ibs = ImBootstrap.instance();
            ibs.setConnectorListener(new ImBootstrap.ConnectorListener() {
                @Override
                public void pushData(Protocol info) {
                    try {
                        Class cls = Class.forName("com.wzm.appdemos.im.entity.header"+info.getHeader());
                        Method method=cls.getMethod("sendReceiver",Context.class,Protocol.class);
                        method.invoke(ClassMap.getInstance(cls),appContext,info);
                    }catch (Exception e){
                        Log.e(TAG,"error="+e.getMessage());
                    }
                }
            });
        }catch (Exception e ){
            e.printStackTrace();
            Log.e("server error:",e.getMessage());
        }
    }
}
