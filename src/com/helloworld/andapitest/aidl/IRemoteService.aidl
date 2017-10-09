package com.helloworld.andapitest.aidl;

/**
 * Created by babycomingin100days on 2017/6/13.
 */
interface IRemoteService {
 /** Request the process ID of this service, to do evil things with it. */
    String getPid();

    /** Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,double aDouble, String aString);

    void startActivity();
}
