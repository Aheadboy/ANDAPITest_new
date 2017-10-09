package com.helloworld.andapitest.threadCommunication;

import android.content.Context;
import android.os.*;
import android.widget.Toast;

/**
 * Created by babycomingin100days on 2017/3/29.
 */
public  class WomenHdlThread extends HandlerThread {
    Context ctx=null;
    private  final static int HELLO = 1;
    private  final static int IAM = 2;
    private  final static int WHATSYOURNAME = 3;

    public WomenHdlThread(String name,Context ctx) {
        super(name);
        this.ctx=ctx;
    }
}
