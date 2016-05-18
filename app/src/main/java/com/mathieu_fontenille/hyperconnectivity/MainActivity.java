package com.mathieu_fontenille.hyperconnectivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent service = new Intent(this, ListenerService.class);
        getApplicationContext().startService(service);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
