/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wzm.im.imadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wzm.im.EaseChatMessageList;
import com.wzm.im.R;
import com.wzm.im.imbean.EMMessage;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class EaseMessageAdapter extends BaseAdapter {

    private final static String TAG = "EaseMessageAdapter";

    private Context context;
    private static final int HANDLER_MESSAGE_REFRESH_LIST = 0;
    private static final int HANDLER_MESSAGE_SELECT_LAST = 1;
    private static final int HANDLER_MESSAGE_SEEK_TO = 2;

    // reference to conversation object in chatsdk
    EMMessage[] messages = null;


    private EaseChatMessageList.MessageListItemClickListener itemClickListener;


    private ListView listView;

    private LayoutInflater inflater;
    private String conId;
    private List<EMMessage> var;
    private List<EMMessage> newsList;
    private int chatType;

    public EaseMessageAdapter(Context context, String username, int chatType, String conId, ListView listView) {
        this.context = context;
        this.listView = listView;
        this.conId = conId;
        this.chatType = chatType;
//        toChatUsername = username;
        inflater = LayoutInflater.from(context);
//        dao = new MessageDao(context);
//		this.conversation = EMClient.getInstance().chatManager().getConversation(username, EaseCommonUtils.getConversationType(chatType), true);
    }

    public EaseMessageAdapter(Context context, String username, int chatType, List<EMMessage> newsList, String conId, ListView listView) {
        this.context = context;
        this.listView = listView;
        this.conId = conId;
        this.newsList = newsList;
        this.chatType = chatType;
        inflater = LayoutInflater.from(context);
    }

    Handler handler = new Handler() {
        private void refreshList() {
            // you should not call getAllMessages() in UI thread
            // otherwise there is problem when refreshing UI and there is new message arrive
//			var=new ArrayList<EMMessage>();
//            if (conId.equals("history")) {
                var = newsList;
//            } else {
//                Log.d(TAG, "conId=" + conId);
//                var = IMCache.messageLists;
//            }

            //集合按时间排序
            Collections.sort(var, new Comparator<EMMessage>() {
                @Override
                public int compare(EMMessage lhs, EMMessage rhs) {
                    int i = (int) (lhs.getTimetamps() - rhs.getTimetamps());
                    return i;
                }
            });
            Log.d(TAG, var.toString());
//			java.util.List<EMMessage> var = new ArrayList<>();
//			var.addAll(IMCache.messageLists);
            messages = var.toArray(new EMMessage[var.size()]);

//			conversation.markAllMessagesAsRead();
            notifyDataSetChanged();
            Log.e("EaseMessageAdapter", "time5="+System.currentTimeMillis());
        }

        @Override
        public void handleMessage(android.os.Message message) {
            switch (message.what) {
                case HANDLER_MESSAGE_REFRESH_LIST:
                    refreshList();
                    break;
                case HANDLER_MESSAGE_SELECT_LAST:
                    if (messages.length > 0) {
                        listView.setSelection(messages.length - 1);
                    }
                    break;
                case HANDLER_MESSAGE_SEEK_TO:
                    int position = message.arg1;
                    listView.setSelection(position);
                    break;
                default:
                    break;
            }
        }
    };

    public void refresh() {
        if (handler.hasMessages(HANDLER_MESSAGE_REFRESH_LIST)) {
            return;
        }
        android.os.Message msg = handler.obtainMessage(HANDLER_MESSAGE_REFRESH_LIST);
        handler.sendMessage(msg);
    }

    /**
     * refresh and select the last
     */
    public void refreshSelectLast() {
        final int TIME_DELAY_REFRESH_SELECT_LAST = 100;
        handler.removeMessages(HANDLER_MESSAGE_REFRESH_LIST);
        handler.removeMessages(HANDLER_MESSAGE_SELECT_LAST);
        handler.sendEmptyMessageDelayed(HANDLER_MESSAGE_REFRESH_LIST, TIME_DELAY_REFRESH_SELECT_LAST);
        handler.sendEmptyMessageDelayed(HANDLER_MESSAGE_SELECT_LAST, TIME_DELAY_REFRESH_SELECT_LAST);
    }

    /**
     * refresh and seek to the position
     */
    public void refreshSeekTo(int position) {
        handler.sendMessage(handler.obtainMessage(HANDLER_MESSAGE_REFRESH_LIST));
        android.os.Message msg = handler.obtainMessage(HANDLER_MESSAGE_SEEK_TO);
        msg.arg1 = position;
        handler.sendMessage(msg);
    }


    public EMMessage getItem(int position) {
        if (messages != null && position < messages.length) {
            return messages[position];
        }
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    /**
     * get count of messages
     */
    public int getCount() {
        return messages == null ? 0 : messages.length;
    }


    @SuppressLint("NewApi")
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.msg_item,null);
            vh=new ViewHolder();
            vh.mTvContent=(TextView)convertView.findViewById(R.id.tv_content);
            vh.mTvName=(TextView)convertView.findViewById(R.id.tv_name);
            convertView.setTag(vh);
        }else {
            vh=(ViewHolder) convertView.getTag();
        }
        EMMessage message = getItem(position);
        if(message.getFromId().equals("111")){
            vh.mTvName.setText("我：");
        }else{
            vh.mTvName.setText(message.getFromId());
        }
        vh.mTvContent.setText(message.getContent());
        return convertView;
    }
    public void setItemClickListener(EaseChatMessageList.MessageListItemClickListener listener) {
        itemClickListener = listener;
    }
    class ViewHolder {
        TextView mTvName;
        TextView mTvContent;
    }

}
