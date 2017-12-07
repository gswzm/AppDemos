package com.wzm.appdemos.im.socket;

import android.util.Log;


import com.google.gson.Gson;
import com.wzm.appdemos.im.utils.IMCache;
import com.wzm.appdemos.im.utils.Protocol;
import com.wzm.appdemos.im.utils.SetRequestMessage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class ImHandle extends ChannelInboundHandlerAdapter{

    private static String TAG="ImHandle";
    private ImRevCB revCB;
    private static ByteBuf HEARTBEAT_SEQUENCE = Unpooled
            .unreleasableBuffer(Unpooled.copiedBuffer("HeartBeat",
                    CharsetUtil.UTF_8));
    private ImBootstrap nettyClient;
    private int heartbeatCount=0;
    public ImHandle(ImRevCB revCB,ImBootstrap nettyClient){
        this.revCB=revCB;
        this.nettyClient=nettyClient;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

       String req=(String)msg;
        Log.d(TAG,"rev="+req);
        Protocol info = new Gson().fromJson(req,Protocol.class);
        revCB.revMsg(info);



    }
    protected void sendPingMsg(ChannelHandlerContext context) {
        IMClient.sendData1();
        heartbeatCount++;
        System.out.println("client sent ping msg to " + context.channel().remoteAddress() + ", count: " + heartbeatCount);
    }
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // IdleStateHandler 所产生的 IdleStateEvent 的处理逻辑.
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case READER_IDLE:
                    handleReaderIdle(ctx);
                    break;
                case WRITER_IDLE:
                    handleWriterIdle(ctx);
                    break;
                case ALL_IDLE:
                    handleAllIdle(ctx);
                    break;
                default:
                    break;
            }
        }
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Log.d("ClientHandler", "-------重连回调------");
        IMCache.isConnected=false;
        ctx.channel().close();
        Protocol info=new Protocol();
//        info.setCmd((short) 10);
//        info.setOpera((short)31);
//        info.setData("连接断开");
        revCB.revMsg(info);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Log.d("NettyClientHandl", "registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Log.d("NettyClientHandler", "=====连接成功回调=====");
        IMCache.isConnected=true;

        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Log.d("NettyClientHandl", "网络异常!"+cause.getMessage());
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        heartbeatCount = 0;
        System.out.println("channel 通道 Read 读取 Complete 完成");
        ctx.flush();
    }

    private void handleReaderIdle(ChannelHandlerContext ctx) {
        if(heartbeatCount == 3){
            ctx.close();
        }
        System.err.println("---READER_IDLE---");

    }

    private void handleWriterIdle(ChannelHandlerContext ctx) {
        System.err.println("---WRITER_IDLE---");
    }

    private void handleAllIdle(ChannelHandlerContext ctx) {
        System.err.println("---ALL_IDLE---");
        sendPingMsg(ctx);
    }
}
