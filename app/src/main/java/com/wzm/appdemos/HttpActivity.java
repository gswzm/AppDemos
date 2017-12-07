package com.wzm.appdemos;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.ProgressDialogCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.subsciber.IProgressDialog;

public class HttpActivity extends AppCompatActivity {
    private TextView tv;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        init();
    }

    private void init() {
        tv = (TextView) findViewById(R.id.tv_content);
        button = (Button) findViewById(R.id.bt_request);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLoginMsg();
            }
        });
    }

    private void getLoginMsg() {
        IProgressDialog mProgressDialog = new IProgressDialog() {
            @Override
            public Dialog getDialog() {
                ProgressDialog dialog = new ProgressDialog(HttpActivity.this);
                dialog.setMessage("登录中...");
                return dialog;
            }
        };
//        EasyHttp.get("/d/town/index?lat=36.061089&lon=103.834304")
        EasyHttp.post("a/login")
                .params("username", "mayy")
                .params("password", "1234567")
//                .timeStamp(true)
                .execute(
                        new ProgressDialogCallBack<Object>(mProgressDialog, true, true) {

                            @Override
                            public void onSuccess(Object infoYLJDResult) {

                                AccountInfo info = new Gson().fromJson((String) infoYLJDResult, AccountInfo.class);
                                tv.setText(info.getData().getAddress());
                            }

                            @Override
                            public void onError(ApiException e) {
                                super.onError(e);

                                tv.setText(e.getMessage());
                            }
                        }
                );
    }
}
