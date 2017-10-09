package com.helloworld.andapitest.contentProvider;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;

/**
 * Created by babycomingin100days on 2017/3/31.
 */
public class GetANDContentProvider extends IntentService {
    private final String TAG = this.getClass().getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @par am name Used to name the worker thread, important only for debugging.
     */
    public GetANDContentProvider() {
        super("GetANDContentProvider");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ContentResolver cr = getContentResolver();
        switch (intent.getFlags()) {
            case 0:
                //需要加上权限<uses-permission android:name="android.permission.READ_CONTACTS"/>//否则报如下异常
                // java.lang.SecurityException: Permission Denial: reading com.android.providers.contacts.ContactsProvider2 uri content://com.android.contacts/contacts from pid=5408, uid=10028 requires android.permission.READ_CONTACTS, or grantUriPermission()
            {
                Cursor cs = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                Cursor csData = cr.query(ContactsContract.Data.CONTENT_URI, null, null, null, null);

                Log.e(TAG, "onHandleIntent: " + cs.getCount());
                while (cs != null && cs.getCount() > 1 && cs.moveToNext()) {
                    Log.e(TAG, "Contacts: while :" + cs.getString(cs.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)), null);
                }
                while (csData != null && csData.getCount() > 1 && csData.moveToNext()) {
                    Log.e(TAG, "Data: while :" + csData.getString(csData.getColumnIndex(ContactsContract.Data.DATA1)), null);
                }
                break;
            }
            case 1: {
                Cursor cs = cr.query(CalendarContract.Calendars.CONTENT_URI, null, null, null, null);
                while (cs != null && cs.getCount() > 1 && cs.moveToNext()) {//
                    Log.e(TAG, "Data: Calendars :" + cs.getString(cs.getColumnIndex(CalendarContract.Calendars.CAL_SYNC1)), null);
                }
            }
            break;
            case 2: {
                Cursor cs = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
                while (cs != null && cs.getCount() > 1 && cs.moveToNext()) {//
                    Log.e(TAG, "MediaStore :" + cs.getString(cs.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)), null);
                }
            }
            break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }


    }
}
