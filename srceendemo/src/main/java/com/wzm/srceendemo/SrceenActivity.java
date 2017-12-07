package com.wzm.srceendemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * 类名： SrceenActivity
 * 时间：2017/11/22 16:37
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author wangzm
*/
public class SrceenActivity extends AppCompatActivity {

    private static String TAG="SrceenActivity";
    private ScreenListener screenListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srceen);
        Log.d(TAG, "onCreate: ");
        screenListener=new ScreenListener(this);
        screenListener.begin(new ScreenListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                //屏幕打开了
                Toast.makeText(getApplicationContext(),"屏幕打开了",Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onScreenOn: ");

            }

            @Override
            public void onScreenOff() {
                //屏幕关闭了
                Toast.makeText(getApplicationContext(),"屏幕关闭了",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onScreenOff: ");
            }

            @Override
            public void onUserPresent() {
                //解锁
                Toast.makeText(getApplicationContext(),"解锁",Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onUserPresent: ");
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
