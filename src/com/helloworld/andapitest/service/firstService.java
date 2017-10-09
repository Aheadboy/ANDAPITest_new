package com.helloworld.andapitest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.helloworld.andapitest.util.GetCurProcessName;

/**
 * Created by ljj1 on 2017/3/20.
 * 简单总结：服务默认是在主线程中，基于服务的特性，他很可能是个耗时操作，那么你几乎应该总是在启用工作子线程来处理
 *
 * 服务在其托管进程的主线程中运行，它既不创建自己的线程，也不在单独的进程中运行（除非另行指定）。
 * 这意味着，如果服务将执行任何 CPU 密集型工作或阻止性操作（例如 MP3 播放或联网），
 * 则应在服务内创建新线程来完成这项工作。通过使用单独的线程
 * ，可以降低发生“应用无响应”(ANR) 错误的风险，而应用的主线程仍可继续专注于运行用户与 Activity 之间的交互
 */

/**
 * 简单总结：服务可以被该服务应用（app）之外的其他应用启动，当该服务明确声明为私有时，则不可被其他app所启用。
 * 依据实验，发现不仅在service，destroy之后，就算是进程死亡状态，service还是可以被启动。
 * 这就是所谓周鸿祎说的葫芦娃吧，只要service启动了，进程就启动了，在启动activity什么的都不是难事了。
 *
 * 无论应用是处于启动状态还是绑定状态，抑或处于启动并且绑定状态，
 * 任何应用组件//attention
 * 均可像使用 Activity 那样通过调用 Intent 来使用服务（即使此服务来自另一应用）。
 * 不过，您可以通过清单文件将服务声明为私有服务，并阻止其他应用访问。 使用清单文件声明服务部分将对此做更详尽的阐述。
 */
public class firstService extends Service {
    private  final String TAG = this.getClass().getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * second
     * 当另一个组件（如 Activity）通过调用 startService() 请求启动服务时，系统将调用此方法。
     * 一旦执行此方法，服务即会启动并可在后台无限期运行。 如果您实现此方法，则在服务工作完成后
     * ，需要由您通过调用 stopSelf() 或 stopService() 来停止服务。
     *
     * @param intent
     * @param flags
     * @param startId   startId：代表启动服务的次数，由系统生成。
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: startId is "+startId);//loge will create this code line
        //另一个app调用该service 这个toast也会显示出来，toast的显示是委托给notificationservice的。
//        Toast.makeText(firstService.this,"helloworld app onStartCommand has been ivoked",Toast.LENGTH_SHORT).show();
        if(intent!=null&&intent.getExtras()!=null){
            Log.e(TAG, "onStartCommand:  "+intent.getExtras().get("data"));
        }
//        stopSelf();//这个不管三七二十一直接停止服务//这个更暴力
        new Thread(new Runnable() {//进来几个请求就由几个工作线程完成，从log可以看出多个线程的名称是不一样的。
            @Override
            public void run() {
                try {
//                    Log.e(TAG, "workThd: CurProcess Name is "+ GetCurProcessName.getCurProcessName(firstService.this));
                    Thread.sleep(3000);
                    Log.e(TAG, "workThd: Thread Name is "+ Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //这个service启动工作线程之后，就会继续执行属于他自己主线程的操作。
        stopSelf(startId);//这个会在处理完最后一个请求在停止服务。//这个更友善
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * first
     */
    @Override
    public void onCreate() {
//        Log.e(TAG, "onCreate: has been invoked");
//        Log.e(TAG, "onCreate: CurProcess Name is "+ GetCurProcessName.getCurProcessName(firstService.this));
//        Log.e(TAG, "onCreate: Thread Name is "+ Thread.currentThread().getName());
        super.onCreate();
    }

    /**
     * last
     */
    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: has been invoked");
        super.onDestroy();
    }
}
