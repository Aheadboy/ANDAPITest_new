package com.helloworld.andapitest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by babycomingin100days on 2017/3/20.
 */

/**
 * 多线程service，并行处理任务，为每一intent开启一个新的线程进行处理
 */
public class MultiThreadService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
