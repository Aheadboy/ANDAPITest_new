package com.helloworld.andapitest.service;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.text.DecimalFormat;

/**
 * Created by babycomingin100days on 2017/5/28.
 */
public class MusicPlayerService extends IntentService implements IMusicPlayerService {
    private MediaPlayer mediaPlayer = null;

    public MusicPlayerService() {
        super("MusicPlayerService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        super.onDestroy();
    }

    public MusicState getMediaState() {
        if (mediaPlayer.isPlaying())
            return MusicState.Started;
        if (mediaPlayer.isLooping())
            return MusicState.Looping;
        return MusicState.Nothing;
    }

    public void play() {

    }

    public void stop() {

    }

    public void pause() {

    }

    @Override
    public void next() {

    }

    @Override
    public void pre() {

    }

    /**
     * 播放进度
     * @return
     */
    public int getCurrentPosition(){
        return mediaPlayer.getCurrentPosition();
    }

    /**
     * 播放总长度
     * @return
     */
    public String getDuration(){
//        return mediaPlayer.getDuration()/(1024-16-8);//毫秒转秒
        DecimalFormat df=new DecimalFormat("0.00");
        return df.format((float)(mediaPlayer.getDuration()>>10-mediaPlayer.getDuration()>>4-mediaPlayer.getDuration()>>3));

    }

    /**
     *
     * @param to
     */
    public void seekTo(int to){
        mediaPlayer.seekTo(to);//毫秒
    }

    public enum MusicState {
        Started, Paused, Stopped, Prepared, Nothing, Looping
    }

    public class LocalBinder extends Binder {
        public MusicPlayerService getService() {////官网少了public
            return MusicPlayerService.this;
        }
    }
}
