package com.helloworld.andapitest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

/**
 * Created by babycomingin100days on 2017/3/21.
 */
/**
 * 如果一个服务只是给本进程，本app使用。那么使用bindservice的最好方式就是扩展Binder类。
 * 如果需要跨进程，跨app那么可以考虑Messenger类。这个是串行工作的->“因为 Messenger 会在单一线程中创建包含所有请求的队列，这样您就不必对服务进行线程安全设计。”
 * 如果你希望多线程处理，那么要自己定义AIDL不过这个不推荐，因为比较复杂了。
 */

/**
 * bindservice
 */
public class LocalService extends Service {
    private  final String TAG = this.getClass().getSimpleName();
    //返回给clients的IBinder对象
    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();
    /**
     * 返回LocalService实例，这样外部就能够调用该LocalService的public方法
     */
    public class LocalBinder extends Binder{
        public LocalService getService(){////官网少了public
            return LocalService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public String getString(){
        return "I'm LocalService pulic method";
    }
    public int getRandomNumber(){
        return mGenerator.nextInt(100);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: LocalService");
        super.onDestroy();
    }
}
