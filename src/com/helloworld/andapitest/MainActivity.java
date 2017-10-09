package com.helloworld.andapitest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.Toast;
import com.helloworld.andapitest.activity.Event;
import com.helloworld.andapitest.contentProvider.GetANDContentProvider;
import com.helloworld.andapitest.service.FirstIntentService;
import com.helloworld.andapitest.service.LocalService;
import com.helloworld.andapitest.service.firstService;
import com.helloworld.andapitest.threadCommunication.ManHdlThread;


public class MainActivity extends Activity {


    LocalService mService;
    boolean mBound = false;
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //启动
        findViewById(R.id.btn_startService).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//				Log.e(TAG, "onClick: "+"has been ivoked");//loge will create this code line
//                Toast.makeText(MainActivity.this, "onClick has been ivoked", Toast.LENGTH_SHORT).show();
                startService(new Intent(MainActivity.this, firstService.class));
            }
        });
        //外部终止
        findViewById(R.id.btn_stopService).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //从外部终止通过start的service，如果是service内部自己终结的话，通过stopSelf();方法
                stopService(new Intent(MainActivity.this, firstService.class));
            }
        });
        //启动IntentService，它将自动终止
        findViewById(R.id.btn_startInttService).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //从外部终止通过start的service，如果是service内部自己终结的话，通过stopSelf();方法
                startService(new Intent(MainActivity.this, FirstIntentService.class));
            }
        });
        findViewById(R.id.btn_getbindServiceM1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound) {
                    Toast.makeText(MainActivity.this, mService.getString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.btn_getbindServiceM2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound) {
                    Toast.makeText(MainActivity.this, mService.getRandomNumber() + "", Toast.LENGTH_SHORT).show();//不加“”會奔潰
                }
            }
        });
        findViewById(R.id.btn_UnbindService).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound) {
                    unbindService(mConnection);
                    mBound=false;//這個是必要的，因為 unbindService(mConnection);不會觸發onServiceDisconnected也就不會調用裡面的 mBound=false;
                }
            }
        });
        findViewById(R.id.btn_bindService).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!mBound) {
                    bindService(new Intent(MainActivity.this, LocalService.class), mConnection, Context.BIND_AUTO_CREATE);
//                }
            }
        });
        findViewById(R.id.btn_finishActivity).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
        findViewById(R.id.btn_twoThreadStartConversation).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ManHdlThread man =new ManHdlThread("Male",MainActivity.this);
                //man.start();
//                Handler manHandle = new ManHandleMessage(man.getLooper(),MainActivity.this);
//                Bundle b = new Bundle();b.putString("","default");
//                Message m1 = new Message();m1.setData(b);m1.what=1;
//                manHandle.sendMessage(m1);
                //looper属于谁（线程），这个handle就属于谁（线程）

            }
        });
        findViewById(R.id.btn_startSysContentPro).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos =((Spinner)findViewById(R.id.spn_ANDContentProType)).getSelectedItemPosition();
                Intent it= new Intent(MainActivity.this, GetANDContentProvider.class);
                it.setFlags(pos);
                startService(it);
            }
        });
        findViewById(R.id.eventActivity).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it= new Intent(MainActivity.this, Event.class);

                startActivity(it);
            }
        });

        //bindService,,5.0之后service要求显示调用.就是明确开启具体哪个service
        bindService(new Intent(MainActivity.this, LocalService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
        super.onDestroy();
    }

    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
            Log.e(TAG, "onServiceConnected: ");
        }

        /**
         * 这个好像是意外断开连接时调用，手动调用unbindService貌似不会触发这个方法
         *
         * 確定unbindService或者finish activity不會調用這個方法，但是會觸發 Service的消亡方法。
         *
         * "onServiceDisconnected is only called in extreme situations (crashed / killed)."
         * 未見到何時調用這個方法。
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            Log.e(TAG, "onServiceDisconnected: ");

        }
    };
}
