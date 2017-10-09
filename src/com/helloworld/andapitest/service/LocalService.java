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
 * ���һ������ֻ�Ǹ������̣���appʹ�á���ôʹ��bindservice����÷�ʽ������չBinder�ࡣ
 * �����Ҫ����̣���app��ô���Կ���Messenger�ࡣ����Ǵ��й�����->����Ϊ Messenger ���ڵ�һ�߳��д���������������Ķ��У��������Ͳ��ضԷ�������̰߳�ȫ��ơ���
 * �����ϣ�����̴߳�����ôҪ�Լ�����AIDL����������Ƽ�����Ϊ�Ƚϸ����ˡ�
 */

/**
 * bindservice
 */
public class LocalService extends Service {
    private  final String TAG = this.getClass().getSimpleName();
    //���ظ�clients��IBinder����
    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();
    /**
     * ����LocalServiceʵ���������ⲿ���ܹ����ø�LocalService��public����
     */
    public class LocalBinder extends Binder{
        public LocalService getService(){////��������public
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
