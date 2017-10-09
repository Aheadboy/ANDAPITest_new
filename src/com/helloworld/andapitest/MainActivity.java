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
        //����
        findViewById(R.id.btn_startService).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//				Log.e(TAG, "onClick: "+"has been ivoked");//loge will create this code line
//                Toast.makeText(MainActivity.this, "onClick has been ivoked", Toast.LENGTH_SHORT).show();
                startService(new Intent(MainActivity.this, firstService.class));
            }
        });
        //�ⲿ��ֹ
        findViewById(R.id.btn_stopService).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //���ⲿ��ֹͨ��start��service�������service�ڲ��Լ��ս�Ļ���ͨ��stopSelf();����
                stopService(new Intent(MainActivity.this, firstService.class));
            }
        });
        //����IntentService�������Զ���ֹ
        findViewById(R.id.btn_startInttService).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //���ⲿ��ֹͨ��start��service�������service�ڲ��Լ��ս�Ļ���ͨ��stopSelf();����
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
                    Toast.makeText(MainActivity.this, mService.getRandomNumber() + "", Toast.LENGTH_SHORT).show();//���ӡ���������
                }
            }
        });
        findViewById(R.id.btn_UnbindService).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound) {
                    unbindService(mConnection);
                    mBound=false;//�@���Ǳ�Ҫ�ģ���� unbindService(mConnection);�����|�lonServiceDisconnectedҲ�Ͳ����{���e��� mBound=false;
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
                //looper����˭���̣߳������handle������˭���̣߳�

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

        //bindService,,5.0֮��serviceҪ����ʾ����.������ȷ���������ĸ�service
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
         * �������������Ͽ�����ʱ���ã��ֶ�����unbindServiceò�Ʋ��ᴥ���������
         *
         * �_��unbindService����finish activity�����{���@�����������Ǖ��|�l Service������������
         *
         * "onServiceDisconnected is only called in extreme situations (crashed / killed)."
         * δҊ���Εr�{���@��������
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            Log.e(TAG, "onServiceDisconnected: ");

        }
    };
}
