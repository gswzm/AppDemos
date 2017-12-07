package com.wzm.appdemos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wzm.appdemos.im.ChatActivity;


/**
 * 类名： MainActivity
 * 时间：2017/11/22 16:20
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author wangzm
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 锁屏监听
     * @param view
     */
    public void screenKey(View view) {

//        startActivity(new Intent(this,SrceenActivity.class));
    }

    public void liveRtmp(View view) {
//        startActivity(new Intent(this,DetailActivity.class)
//                .putExtra("liveUrl","rtmp://live.hkstv.hk.lxdns.com/live/hks"));
    }

    public void chat(View view) {
//        startActivity(new Intent(this, ChatActivity.class));
    }

    public void http(View view) {
        startActivity(new Intent(this,HttpActivity.class));
    }
}
