package com.wzm.im;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.wzm.im.imadapter.EaseMessageAdapter;
import com.wzm.im.imbean.EMMessage;

import java.util.List;



public class EaseChatMessageList extends RelativeLayout {

    protected static final String TAG = "EaseChatMessageList";
    protected ListView listView;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected Context context;
    protected int chatType;
    protected String toChatUsername;
    protected EaseMessageAdapter messageAdapter;
    protected boolean showUserNick;
    protected boolean showAvatar;
    protected Drawable myBubbleBg;
    protected Drawable otherBuddleBg;

    public EaseChatMessageList(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public EaseChatMessageList(Context context, AttributeSet attrs) {
        super(context, attrs);
//        parseStyle(context, attrs);
        init(context);
    }

    public EaseChatMessageList(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.ease_chat_message_list, this);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.chat_swipe_layout);
        listView = (ListView) findViewById(R.id.list);
    }

    /**
     * init widget
     * @param toChatUsername
     * @param chatType
     */
    public void init(String toChatUsername, int chatType, String conId) {
        this.chatType = chatType;
        this.toChatUsername = toChatUsername;
        Log.e("EaseChatMessageList","toChatUsername："+toChatUsername);
        Log.e("EaseChatMessageList","conId："+conId);
        messageAdapter = new EaseMessageAdapter(context, toChatUsername, chatType, conId,listView);
        // set message adapter
        listView.setAdapter(messageAdapter);

        refreshSelectLast();
    }

    /**
     * init widget
     * @param toChatUsername
     * @param chatType
     */
    public void init(String toChatUsername, int chatType, List<EMMessage> newsList, String conId) {
        this.chatType = chatType;
        this.toChatUsername = toChatUsername;
        Log.e("EaseChatMessageList","groupId："+toChatUsername);
        messageAdapter = new EaseMessageAdapter(context, toChatUsername, chatType, newsList, conId,listView);

        // set message adapter
        listView.setAdapter(messageAdapter);

        refreshSelectLast();
    }

    /**
     * refresh
     */
    public void refresh(){
        if (messageAdapter != null) {
            messageAdapter.refresh();
        }
    }

    /**
     * refresh and jump to the last
     */
    public void refreshSelectLast(){
        if (messageAdapter != null) {
            messageAdapter.refreshSelectLast();
        }
    }

    /**
     * refresh and jump to the position
     * @param position
     */
    public void refreshSeekTo(int position){
        if (messageAdapter != null) {
            messageAdapter.refreshSeekTo(position);
        }
    }

    public ListView getListView() {
        return listView;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout(){
        return swipeRefreshLayout;
    }

    public EMMessage getItem(int position){
        return messageAdapter.getItem(position);
    }

    public void setShowUserNick(boolean showUserNick){
        this.showUserNick = showUserNick;
    }

    public boolean isShowUserNick(){
        return showUserNick;
    }

    public interface MessageListItemClickListener{
        void onResendClick(EMMessage message);
        /**
         * there is default handling when bubble is clicked, if you want handle it, return true
         * another way is you implement in onBubbleClick() of chat row
         * @param message
         * @return
         */
        boolean onBubbleClick(EMMessage message);
        void onBubbleLongClick(EMMessage message);
        void onUserAvatarClick(String username);
        void onUserAvatarLongClick(String username);
    }

    /**
     * set click listener
     * @param listener
     */
    public void setItemClickListener(MessageListItemClickListener listener){
        if (messageAdapter != null) {
            messageAdapter.setItemClickListener(listener);
        }
    }
}
