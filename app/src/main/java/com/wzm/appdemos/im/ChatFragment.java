package com.wzm.appdemos.im;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.wzm.appdemos.R;

import com.wzm.appdemos.im.service.PullMsgReceiver;
import com.wzm.appdemos.im.socket.IMClient;
import com.wzm.appdemos.im.utils.IMCache;
import com.wzm.appdemos.im.utils.Protocol;
import com.wzm.im.EaseChatMessageList;
import com.wzm.im.imbean.EMMessage;
import com.wzm.im.ui.IMChatBaseFragment;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static android.app.Activity.RESULT_OK;
//import gstb.fd.eofficial.oa.im.widget.EaseVoiceRecorderView;

/**
 * 聊天界面
 * Created by wangzm on 2017/5/13 0013.
 */

public class ChatFragment extends IMChatBaseFragment {
    private static final String TAG = "ChatFragment";
    /**
     * params to fragment
     */
    private Bundle fragmentArgs;



    private EaseChatMessageList messageList;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;

    private boolean isloading;
    private boolean haveMoreData = true;
    private int pagesize = 1;

    static final int ITEM_TAKE_PICTURE = 1;
    static final int ITEM_PICTURE = 2;
    static final int ITEM_LOCATION = 3;
    private static final int REQUEST_CODE_SELECT_FILE = 12;
    private InputMethodManager inputManager;

    private TextView tvFragTitle,tvFragRightText;
    private boolean isMessageListInited;

    private static final int TIME_OUT = 10 * 1000;   //超时时间
    private static final String CHARSET = "utf-8"; //设置编码

    private boolean isMore=true;
//    private PullMsgReceiver pullMsgReceiver;
    private String conId;
    private SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String result;
    private List<EMMessage> msgLists=new ArrayList<>();
    private EMMessage contextMenuMessage;
    private EditText mEtChat;
    private Button mBtnSend;

    private PullMsgReceiver pullMsgReceiver;

//    String header, String roomId, String userId, String device,
// String content, String time, String userPhoto, String userName, int count
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.im_frag_chat, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        fragmentArgs = getArguments();
        // check if single chat or group chat
//        chatType = fragmentArgs.getInt(IMConstants.EXTRA_CHAT_TYPE, IMConstants.CHATTYPE_SINGLE);
        // userId you are chat with or group id
//        toChatUsername=fragmentArgs.getString("userId");
//        toChatUsername = "402880095ad0f075015ad0f075150000";
//        nickName=fragmentArgs.getString("nike");
//        Log.e(TAG,"nikeName="+nickName);
//        conId=fragmentArgs.getString("conId","");
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    protected void initView() {
        messageList = (EaseChatMessageList) getView().findViewById(R.id.message_list);
        listView = messageList.getListView();

        mEtChat=(EditText)getView().findViewById(R.id.et_chat);

        mBtnSend=(Button) getView().findViewById(R.id.bt_send);
        initEvent();

        swipeRefreshLayout = messageList.getSwipeRefreshLayout();
        swipeRefreshLayout.setColorSchemeResources(R.color.holo_blue_bright, R.color.holo_green_light,
                R.color.holo_orange_light, R.color.holo_red_light);
        inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initReceiver();
    }

    @Override
    protected void setUpView() {
        onMessageListInit();
//        setRefreshLayoutListener();
    }

    private void onMessageListInit(){
//        init(String toChatUsername, int chatType, List<EMMessage> newsList, String conId
        messageList.init("", 1,IMCache.messageLists,conId);
        isMessageListInited = true;
    }

    private void initEvent(){
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=mEtChat.getText().toString().trim();
                if("".equals(msg)){
                    Toast.makeText(getContext(),"消息不能为空!",Toast.LENGTH_SHORT).show();
                    return;
                }
                Protocol protocol=new Protocol();
                protocol.setHeader("1");
                protocol.setUserId("123456");
                protocol.setContent(msg);
                protocol.setRoomId("110");
//                EMMessage emMessage=new EMMessage();
//                emMessage.setFromId("111");
//                emMessage.setContent(msg);
//                IMCache.messageLists.add(emMessage);
//                if(isMessageListInited) {
//                    messageList.refreshSelectLast();
//                }
                IMClient.sendData(protocol);


            }
        });
    }

    private void initReceiver(){

        pullMsgReceiver=new PullMsgReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                Protocol info=(Protocol) intent.getSerializableExtra("msg");
                EMMessage emMessage=new EMMessage();
                emMessage.setFromId(info.getUserId());
                emMessage.setContent(info.getContent());
                IMCache.messageLists.add(emMessage);
                if(isMessageListInited) {
                    messageList.refreshSelectLast();
                }

            }
        };
        getActivity().registerReceiver(pullMsgReceiver,new IntentFilter(PullMsgReceiver.CHAT_TEXT_ACTION));
    }
    @Override
    public void onResume() {
        super.onResume();
        if(isMessageListInited)
            messageList.refresh();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(pullMsgReceiver);
    }
    //    private void setRefreshLayoutListener() {
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//
//                        if (listView.getFirstVisiblePosition() == 0 && !isloading && haveMoreData) {
//                            List<EMMessage> messages;
//                            pagesize++;
//                            try {
//                                messages = dao.selectAllChatForPage("".equals(conId)?toChatUsername:conId,
//                                        pagesize);
//                                Log.e(TAG,"============"+pagesize);
////                                IMCache.messageLists.addAll(messages);
//                            } catch (Exception e1) {
//                                swipeRefreshLayout.setRefreshing(false);
//                                return;
//                            }
//                            if (messages.size() > 0) {
//                                messageList.refreshSeekTo(messages.size() - 1);
//                                if (messages.size() != 12) {
//                                    haveMoreData = false;
//                                }
//                            } else {
//                                haveMoreData = false;
//                            }
//
//                            isloading = false;
//
//                        } else {
//                            Toast.makeText(getActivity(), "no more",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 600);
//            }
//        });
//    }
}
