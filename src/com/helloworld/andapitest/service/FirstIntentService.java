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
 * ���ܽ᣺����һ���������̣߳������������е�Intent�������ƴ��й�����ʽ(���ϣ��������ж��̴߳�����ô�㻹��ֱ�Ӽ̳�service��ʵ��һ����MultiThreadService�ķ���)
 * ���й������������ҽ���service
 * ������Ҫ�����ǣ�1���ṩһ�����캯����������캯���������������߳�������2��ʵ��onHandleIntent()������
 * ע�ᣬע�ᣬ�ǵ�ע�ᰡ������������û�з�Ӧ��������Ҳ���������ġ�����
 * <p>
 * ����Ĭ�ϵĹ����̣߳�������Ӧ�õ����߳���ִ�д��ݸ� onStartCommand() ������ Intent��
 * �����������У����ڽ� Intent ��һ���ݸ� onHandleIntent() ʵ�֣�����������Զ���ص��Ķ��߳����⡣
 * �ڴ������������������ֹͣ�����������Զ���ص��� stopSelf()��
 * �ṩ onBind() ��Ĭ��ʵ�֣����� null����
 * �ṩ onStartCommand() ��Ĭ��ʵ�֣��ɽ� Intent ���η��͵��������к� onHandleIntent() ʵ�֡�
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

        //������Intent������ЩIntent ���������ˣ�����onDestroy
        //������������Ͷ���һ�������߳�����ɡ��Ŷ���ɡ�
        //�����5��������ť
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
