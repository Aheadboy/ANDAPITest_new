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
 * 服务端步骤：
 * 1、声明Messenger对象；2、通过onBind返回mMessenger.getBinder();3、等待客户端发消息到handleMessage;
 * 4、处理结果通过msgfromClient.replyTo.send(msgToClient);返回
 */
public class MessengerService extends Service {
    private static final int MSG_SUM = 0x110;
    private final String TAG = this.getClass().getSimpleName();
    HandlerThread hdlTrd = null;
    Handler handlerOnWorkTrd = null;
    //普通handle形式，在主线程运行
    Handler handlerOnMainTrd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            doSameWork(msg);
            super.handleMessage(msg);
        }
    };
    //最好换成HandlerThread的形式
    private Messenger mMessenger = null;
    public MessengerService() {
        super();
        //HandleThread形式
        //新建了一个子线程进行处理
        this.hdlTrd = new HandlerThread("MSHdlTrd");
        hdlTrd.start();//记得start否则娶不到这个线程的looper，没有looper 就没有handle，没有handle就没法与外部通信
        this.handlerOnWorkTrd=new Handler(hdlTrd.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                doSameWork(msg);
                super.handleMessage(msg);
            }
        };
        this.mMessenger=new Messenger(handlerOnWorkTrd);//服务在子线程处理请求消息。//handle在工作线程
//        this.mMessenger=new Messenger(handlerOnMainTrd);//服务在主线程处理请求消息//handle在主线程
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
                    //耗时操作
                    Toast.makeText(MessengerService.this, "MessengerService Thread Name" + Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
                    Thread.sleep(1);
                    //客户端传来两个数据，进行处理
                    msgToClient.arg2 = msgfromClient.arg1 + msgfromClient.arg2;
                    //处理结果返回,貌似来的那个Message知道自己是从哪里来的，所以通过来的那个Message进行replyTo就可以进行回信的感觉
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
