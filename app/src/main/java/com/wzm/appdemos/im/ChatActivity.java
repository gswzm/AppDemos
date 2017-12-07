package com.wzm.appdemos.im;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.wzm.appdemos.R;
import com.wzm.appdemos.im.service.IMHelper;


/**
 * 聊天界面
 * author wangzm
 * time 2017/5/13
 */
public class ChatActivity extends FragmentActivity {

    private static String TAG="ChatActivity";
    public static ChatActivity activityInstance;
    String toChatUsername;
    ChatFragment chatFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.im_activity_chat);
        IMHelper.getInstance().init(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//        }
//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(R.color.white);//通知栏所需颜色
        chatFragment=new ChatFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // make sure only one chat activity is opened
        String username = intent.getStringExtra("userId");
    }
}
