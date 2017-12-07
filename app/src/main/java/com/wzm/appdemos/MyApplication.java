package com.wzm.appdemos;

import android.app.Application;
import android.content.Context;

import com.wzm.appdemos.im.service.IMHelper;
import com.wzm.utilslib.UtilsLibHelper;
import com.wzm.utilslib.http.constant.AppConstant;
import com.wzm.utilslib.http.interceptor.CustomSignInterceptor;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.SerializableDiskConverter;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.model.HttpHeaders;
import com.zhouyou.http.model.HttpParams;

/**
 * 类名：PACKAGE_NAME
 * 时间：2017/11/27 20:48
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author wangzm
 */

public class MyApplication extends Application {

    /**
     * 单列模式
     **/
    private static MyApplication instance = null;
    public static Context applicationContext;


    /**
     * 公有的静态函数，对外暴露获取单例对象的接口
     **/
    public static MyApplication getInstance() {

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        instance = this;
        IMHelper.getInstance().init(this);
        UtilsLibHelper.getInstance().init(this,Contants.Url);
    }
}
