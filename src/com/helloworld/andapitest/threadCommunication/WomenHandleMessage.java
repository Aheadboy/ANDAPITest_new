package com.helloworld.andapitest.threadCommunication;

import android.content.Context;
import android.os.*;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by babycomingin100days on 2017/3/30.
 */
public class WomenHandleMessage extends Handler {
    private final String TAG = this.getClass().getSimpleName();

    public WomenHandleMessage(Looper looper, Context ctx, HandlerThread thread){
        super(looper);
        this.ctx=ctx;
        this.manHandleMessage = new ManHandleMessage(thread.getLooper(),ctx);
    }
    Context ctx=null;
    private  final static int HELLO = 1;
    private  final static int IAM = 2;
    private  final static int WHATSYOURNAME = 3;
    private ManHandleMessage manHandleMessage=null;
    @Override
    public void handleMessage(Message msgFromOther) {
        super.handleMessage(msgFromOther);
        switch (msgFromOther.what) {
            case HELLO:
                Toast.makeText(ctx, "Man say Hello to me", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Man say Hello to me");
                Bundle b = new Bundle();b.putString("HELLO","Hi");
                Message m1 = Message.obtain(msgFromOther);m1.what=HELLO;m1.setData(b);
//                    msgFromOther.replyTo.send(m1);//msgFromOther.replyTo为空，不知道什么原因
                    manHandleMessage.sendMessage(m1);
                break;
            case IAM:
                Toast.makeText(ctx, "His name is "+msgFromOther.arg1, Toast.LENGTH_SHORT).show();
                Log.e(TAG, "His name is "+msgFromOther.arg1);
                Bundle b2 = new Bundle();b2.putString("IAM","Hi "+msgFromOther.arg1+" my age is 18" );
                Message m2 =new Message();m2.what=IAM;m2.setData(b2);
                manHandleMessage.sendMessage(m2);
//                    msgFromOther.replyTo.send(m2);
                break;
            case WHATSYOURNAME:
                Toast.makeText(ctx, "He asked My Name", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "He asked My Name");

                Bundle b3 = new Bundle();b3.putString("WHATSYOURNAME","My Name is lucy");
                Message m3 =new Message();m3.what=WHATSYOURNAME;m3.setData(b3);
                manHandleMessage.sendMessage(m3);
//                    msgFromOther.replyTo.send(m3);
            default:
//                Toast.makeText(ctx, "He asked My Name", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
