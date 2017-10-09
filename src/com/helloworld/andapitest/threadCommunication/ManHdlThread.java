package com.helloworld.andapitest.threadCommunication;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by babycomingin100days on 2017/3/29.
 */
public class ManHdlThread extends HandlerThread {
    private final  String TAG = this.getClass().getSimpleName();
    Context ctx=null;
    private  final static int HELLO = 1;
    private  final static int IAM = 2;
    private  final static int WHATSYOURNAME = 3;
    public ManHdlThread(String name, Context ctx) {
        super(name);
        this.ctx=ctx;
        HandlerThread women = new WomenHdlThread("Female",ctx);
        women.start();
        ManHdlThread.this.start();////
        Handler womenHandle = new WomenHandleMessage(women.getLooper(),ctx,ManHdlThread.this);

        Message m1 = new Message();
        m1.what=1;
        Message m2 = new Message();
        m2.what=2;
        Message m3 = new Message();
        m3.what=3;
        womenHandle.sendMessage(m1);
        womenHandle.sendMessage(m2);
        womenHandle.sendMessageAtFrontOfQueue(m3);


    }

}
