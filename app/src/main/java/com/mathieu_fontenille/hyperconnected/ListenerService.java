package com.mathieu_fontenille.hyperconnected;

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

    private void setSharedPrefValue(String lineToUpdate, int valueToPush, SharedPreferences prefs)
    {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(lineToUpdate, valueToPush);
        editor.commit();
        Log.d("Hypercov2", lineToUpdate + ": counter incremented " + valueToPush);
    }
    private int getValueFromSharedPref(String lineToGet, SharedPreferences prefs)
    {
        return prefs.getInt(lineToGet, 0);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final SharedPreferences prefs = this.getSharedPreferences(
                "com.mathieu_fontenille.hyperconnected", Context.MODE_PRIVATE);


        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                    Integer sharedCount = getValueFromSharedPref("count", prefs);
                    Integer sharedCountWeek = getValueFromSharedPref("weeklyCount", prefs);
                    sharedCount = sharedCount+1;
                    sharedCountWeek = sharedCountWeek+1;
                    setSharedPrefValue("count", sharedCount, prefs);
                    setSharedPrefValue("weeklyCount", sharedCountWeek, prefs);
                }
            }
        }, intentFilter);

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO for communication return IBinder implementation
        return null;
    }


}

