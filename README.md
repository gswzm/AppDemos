# 这里有常用的工具集合

### 其中异常错误日志和网络访问依赖方法
[![](https://jitpack.io/v/gswzm/AppDemos.svg)](https://jitpack.io/#gswzm/AppDemos)

1.   在主程序`builde.gradle`中添加
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

2.  在app的`builde.gralde`中添加依赖

```
dependencies {
    compile 'com.github.gswzm:AppDemos:1.0'
}
```
3.  在`application`中初始化 需要传两个参数 第二个参数是网络访问的公共接口

```
  @Override
    public void onCreate() {
        super.onCreate();
      
        UtilsLibHelper.getInstance().init(this,Contants.Url);
    }
```
