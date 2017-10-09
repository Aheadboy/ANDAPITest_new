package com.helloworld.andapitest.threadCommunication;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by babycomingin100days on 2017/3/30.
 */
public class ManHandleMessage extends Handler {
    private final String TAG = this.getClass().getSimpleName();
    public ManHandleMessage(Looper looper,Context ctx){
        super(looper);
        this.ctx=ctx;
    }
    Context ctx=null;
    private  final static int HELLO = 1;
    private  final static int IAM = 2;
    private  final static int WHATSYOURNAME = 3;
    @Override
    public void handleMessage(Message msgFromOther) {
        super.handleMessage(msgFromOther);
        switch (msgFromOther.what) {
            case HELLO:
                String reply=msgFromOther.getData().getString("HELLO");
                Toast.makeText(ctx, "She say "+reply+" to me", Toast.LENGTH_SHORT).show();
                Log.e(TAG,  "She say "+reply+" to me");
                break;
            case IAM:
                String reply2=msgFromOther.getData().getString("IAM");
                Toast.makeText(ctx, "she says .. "+reply2, Toast.LENGTH_SHORT).show();
                Log.e(TAG,   "she says .. "+reply2);
                break;
            case WHATSYOURNAME:
                String reply3=msgFromOther.getData().getString("WHATSYOURNAME");
                Toast.makeText(ctx, "she says : "+reply3, Toast.LENGTH_SHORT).show();
                Log.e(TAG,  "she says : "+reply3);
            default:
//                String reply4=msgFromOther.getData().getString("");
//                Toast.makeText(ctx, "she says : "+reply4, Toast.LENGTH_SHORT).show();
//                Log.e(TAG,"REPLY4");
                break;
        }
    }
}
