package feicui.newsapp02.activity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import feicui.newsapp02.R;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class MyService extends Service {
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {//创建
        mediaPlayer = MediaPlayer.create(this, R.raw.aa);//实例多媒体播放器
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);
    }

    @Override//启动方法
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            int i = bundle.getInt("number");
            switch (i) {
                case 1:
                    if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                        mediaPlayer.start();
                    }
                    break;
                case 2:
                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                    }
                    break;
                case 3:
                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                    break;
                case 4:
                    stopSelf();
                    break;
            }
        }


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}