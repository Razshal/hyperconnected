package com.mathieu_fontenille.hyperconnectivity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import static android.app.PendingIntent.getActivity;


public class ListenerService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO do something useful

  //      SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        SharedPreferences settings = getSharedPreferences("HypercoPrefs", 0);
        boolean silent = settings.getBoolean("silentMode", false);

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                    Log.d("Service", "Service lancé");
                }
            }
        }, intentFilter);
        //smsHandler.sendEmptyMessageDelayed(DISPLAY_DATA, 1000);
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO for communication return IBinder implementation
        return null;
    }


}

