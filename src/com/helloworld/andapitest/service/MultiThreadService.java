package com.helloworld.andapitest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by babycomingin100days on 2017/3/20.
 */

/**
 * ���߳�service�����д�������Ϊÿһintent����һ���µ��߳̽��д���
 */
public class MultiThreadService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
