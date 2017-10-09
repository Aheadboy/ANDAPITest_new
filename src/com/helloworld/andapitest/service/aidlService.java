package com.helloworld.andapitest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import com.helloworld.andapitest.activity.CanvasActivity;
import com.helloworld.andapitest.aidl.IRemoteService;

/**
 * Created by babycomingin100days on 2017/6/13.
 */
public class aidlService extends Service {

    IRemoteService.Stub mBinder = new IRemoteService.Stub() {
        @Override
        public String getPid() throws RemoteException {
            return "I am from another app! My Pid is : " + Process.myPid();
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void startActivity() {
            Intent it = new Intent(aidlService.this, CanvasActivity.class);
//            startActivity(it);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}
