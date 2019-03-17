package com.mygdx.game;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class Servicio extends Service {
    private Handler mHandler;
    private int i = 1;
    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
    }

    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(ToastTask);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler.postDelayed(ToastTask, 3000); // Starts the loop here
        super.onStartCommand(intent, flags, startId);
        return Service.START_STICKY;
    }

    private Runnable ToastTask = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(Servicio.this, "El toast se ha lanzado " + i+" veces" , Toast.LENGTH_SHORT).show();
            i++;
            if (i > 5) {
                stopSelf();
            }
            mHandler.postDelayed(this, 3000);
        }
    };
}