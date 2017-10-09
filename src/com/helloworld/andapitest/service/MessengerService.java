package com.helloworld.andapitest.service;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by babycomingin100days on 2017/3/21.
 */

/**
 * ����˲��裺
 * 1������Messenger����2��ͨ��onBind����mMessenger.getBinder();3���ȴ��ͻ��˷���Ϣ��handleMessage;
 * 4��������ͨ��msgfromClient.replyTo.send(msgToClient);����
 */
public class MessengerService extends Service {
    private static final int MSG_SUM = 0x110;
    private final String TAG = this.getClass().getSimpleName();
    HandlerThread hdlTrd = null;
    Handler handlerOnWorkTrd = null;
    //��ͨhandle��ʽ�������߳�����
    Handler handlerOnMainTrd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            doSameWork(msg);
            super.handleMessage(msg);
        }
    };
    //��û���HandlerThread����ʽ
    private Messenger mMessenger = null;
    public MessengerService() {
        super();
        //HandleThread��ʽ
        //�½���һ�����߳̽��д���
        this.hdlTrd = new HandlerThread("MSHdlTrd");
        hdlTrd.start();//�ǵ�start����Ȣ��������̵߳�looper��û��looper ��û��handle��û��handle��û�����ⲿͨ��
        this.handlerOnWorkTrd=new Handler(hdlTrd.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                doSameWork(msg);
                super.handleMessage(msg);
            }
        };
        this.mMessenger=new Messenger(handlerOnWorkTrd);//���������̴߳���������Ϣ��//handle�ڹ����߳�
//        this.mMessenger=new Messenger(handlerOnMainTrd);//���������̴߳���������Ϣ//handle�����߳�
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }


    private void doSameWork(Message msgfromClient) {
        Message msgToClient = Message.obtain(msgfromClient);
        switch (msgfromClient.what) {
            case MSG_SUM:
                msgToClient.what = MSG_SUM;
                try {
                    //��ʱ����
                    Toast.makeText(MessengerService.this, "MessengerService Thread Name" + Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
                    Thread.sleep(1);
                    //�ͻ��˴����������ݣ����д���
                    msgToClient.arg2 = msgfromClient.arg1 + msgfromClient.arg2;
                    //����������,ò�������Ǹ�Message֪���Լ��Ǵ��������ģ�����ͨ�������Ǹ�Message����replyTo�Ϳ��Խ��л��ŵĸо�
                    msgfromClient.replyTo.send(msgToClient);
                } catch (InterruptedException e) {

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
        Log.e(TAG, Thread.currentThread().getName());
    }
}
