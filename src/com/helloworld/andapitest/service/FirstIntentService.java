package com.helloworld.andapitest.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.helloworld.andapitest.util.GetCurProcessName;


/**
 * Created by babycomingin100days on 2017/3/20.
 */

/**
 * 简单总结：创建一个工作子线程，用来处理所有的Intent请求。类似串行工作方式(如果希望服务进行多线程处理，那么你还是直接继承service，实现一个叫MultiThreadService的服务)
 * 所有工作结束后，自我结束service
 * 所必须要做的是：1、提供一个构造函数（这个构造函数用来命名工作线程名）；2、实现onHandleIntent()方法体
 * 注册，注册，记得注册啊。否则点击启动没有反应啊。。。也不报错，坑哪。。。
 * <p>
 * 创建默认的工作线程，用于在应用的主线程外执行传递给 onStartCommand() 的所有 Intent。
 * 创建工作队列，用于将 Intent 逐一传递给 onHandleIntent() 实现，这样您就永远不必担心多线程问题。
 * 在处理完所有启动请求后停止服务，因此您永远不必调用 stopSelf()。
 * 提供 onBind() 的默认实现（返回 null）。
 * 提供 onStartCommand() 的默认实现，可将 Intent 依次发送到工作队列和 onHandleIntent() 实现。
 */
public class FirstIntentService extends IntentService {
    private final String TAG = this.getClass().getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     * <p>
     * name Used to name the worker thread, important only for debugging.
     */
    public FirstIntentService() {
        super("this is work thread name");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //传入多个Intent，将这些Intent 都处理完了，调用onDestroy
        //进来几个请求就都有一个工作线程来完成。排队完成。
        //点击了5次启动按钮
//        03-20 21:43:19.051 26898-27257/com.helloworld.andapitest E/FirstIntentService: onHandleIntent: IntentService[this is work thread name]
//        03-20 21:43:22.055 26898-27257/com.helloworld.andapitest E/FirstIntentService: onHandleIntent: IntentService[this is work thread name]
//        03-20 21:43:25.059 26898-27257/com.helloworld.andapitest E/FirstIntentService: onHandleIntent: IntentService[this is work thread name]
//        03-20 21:43:28.063 26898-27257/com.helloworld.andapitest E/FirstIntentService: onHandleIntent: IntentService[this is work thread name]
//        03-20 21:43:31.067 26898-27257/com.helloworld.andapitest E/FirstIntentService: onHandleIntent: IntentService[this is work thread name]
//        03-20 21:43:34.071 26898-26898/com.helloworld.andapitest E/FirstIntentService: onDestroy: FirstIntentService
        Toast.makeText(this, "onHandleIntent", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onCreate: CurProcess Name is " + GetCurProcessName.getCurProcessName(FirstIntentService.this));
        if (null != intent && intent.getExtras() != null) {
            Log.e(TAG, "onHandleIntent: other app give me message is " + intent.getExtras().get("data"));
        }
        Log.e(TAG, "onHandleIntent: " + Thread.currentThread().getName());///this is work thread name
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: " + TAG + "");
        super.onDestroy();
    }
}
