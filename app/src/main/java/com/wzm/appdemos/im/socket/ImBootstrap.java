package com.wzm.appdemos.im.socket;

import android.util.Log;

import com.wzm.appdemos.im.utils.IMCache;
import com.wzm.appdemos.im.utils.IMConstants;
import com.wzm.appdemos.im.utils.Protocol;

import java.util.concurrent.TimeUnit;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class ImBootstrap {
    private static final String TAG="ImBootstrap";
    private int port;
    private String host;
    private SocketChannel socketChannel;
    private static ImBootstrap ibs;
    private ConnectorListener mListener;
    private Bootstrap bootstrap;
    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();
    private static final EventExecutorGroup group = new DefaultEventExecutorGroup(20);
    private ImBootstrap(int port, String host) throws InterruptedException {
        this.port = port;
        this.host = host;
        start();
    }

    public SocketChannel channel(){
        return this.socketChannel;
    }

    public static ImBootstrap instance() throws InterruptedException {
        if(ibs == null){
            ibs = new ImBootstrap(IMConstants.PORT,IMConstants.URL);
        }
        //TODO IDLE
        return ibs;
    }
    public void start() throws InterruptedException {
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        bootstrap=new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE,true);
        bootstrap.group(eventLoopGroup);
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000);
        bootstrap.remoteAddress(host,port);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new IdleStateHandler(5,5,5, TimeUnit.SECONDS));
                socketChannel.pipeline().addLast(new StringDecoder());
//                socketChannel.pipeline().addLast(DECODER);
//                socketChannel.pipeline().addLast(ENCODER);
                socketChannel.pipeline().addLast(new ImHandle(new ImRevCB() {
                    @Override
                    public void revMsg(Protocol info) {
                        if(mListener!=null){
                            mListener.pushData(info);
                        }
                    }
                },ibs));
            }
        });
        doConnect();

    }
    public void doConnect(){
        if(IMCache.isConnected) {
            return;
        }
        ChannelFuture future=null;
        try {
            future =bootstrap.connect(host,port).sync();
            if(future.isSuccess()){
                IMCache.isConnected=true;
                socketChannel = (SocketChannel)future.channel();
                Log.d("bootstrap","connect server  成功---------");
            }
//            future.addListener(new ConnectionListener(this));
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "本地网络已断开");
            if(ibs.channel()!=null){
                ibs.channel().close();
            }
            Protocol info=new Protocol();
            info.setHeader("44");
            info.setContent("连接断开");
            IMCache.isConnected=false;
            mListener.pushData(info);
        }

    }

    class ConnectionListener implements ChannelFutureListener {

        private ImBootstrap client;

        public ConnectionListener(ImBootstrap client) {

            this.client = client;

        }

        @Override

        public void operationComplete(final ChannelFuture channelFuture) throws Exception {

            Log.i("状态",channelFuture.isSuccess()+"");
            if (!channelFuture.isSuccess()) {
                System.out.println("Reconnect");
                IMCache.isConnected=false;
                final EventLoop loop = channelFuture.channel().eventLoop();
                loop.schedule(new Runnable() {

                    @Override

                    public void run() {
                        try {
                            client.doConnect();
                        }catch (Exception e){
                            Log.e("ImBootstrap",e.getMessage());
                        }

                    }

                }, 5, TimeUnit.SECONDS);

            }else{
                IMCache.isConnected=true;
                socketChannel = (SocketChannel)channelFuture.channel();
                Log.d("bootstrap","connect server  成功---------");
            }

        }

    }
    public void setConnectorListener(ConnectorListener listener) {
        this.mListener = listener;
    }

    public interface ConnectorListener {
        void pushData(Protocol info);
    }
}
