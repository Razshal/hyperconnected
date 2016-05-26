package com.mathieu_fontenille.hyperconnected;

import android.app.ActivityManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebViewFragment;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView counter;
    Button resetButton;
    Handler handler = new Handler();
    public SharedPreferences prefs;
    private Runnable updateData;

    private boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)){
            if ("com.mathieu_fontenille.hyperconnected.ListenerService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    private void serviceAutoCheckAndLaunch(){
        if (!isServiceRunning()) {
            Intent service = new Intent(this, ListenerService.class);
            getApplicationContext().startService(service);
            Log.d("hypercov2", "Service check OK, launching service");
        }
    }
    protected void updateCount()
    {
        Integer sharedCount = prefs.getInt("count", 0);
        if (sharedCount == 0)
            counter.setText("0");
        else
            counter.setText(Integer.toString(sharedCount));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        WeeklyFragment weFragment=new WeeklyFragment();
        transaction.replace(R.id.fragmentContainer, (weFragment));
        transaction.commit();
*/
        serviceAutoCheckAndLaunch();
        counter = (TextView) findViewById(R.id.count);
        prefs = this.getSharedPreferences("com.mathieu_fontenille.hyperconnected", Context.MODE_PRIVATE);
        updateCount();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    protected void onResume() {
        super.onResume();
        serviceAutoCheckAndLaunch();
        updateCount();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void createFragmentView(Fragment fragmentToPlace){


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {



        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = new Fragment();
        Class Fragmentclass;

        if (id == R.id.nav_today) {

        } else if (id == R.id.nav_week) {
 /*           android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            WeeklyFragment weFragment=new WeeklyFragment();
            transaction.replace(R.id.fragmentContainer, (weFragment));
            transaction.commit();
            Log.d("hypercov2", "salut, c\'est le nav drawer");
            */
        } else if (id == R.id.nav_month) {


        } else if (id == R.id.nav_year) {

        } else if (id == R.id.nav_share) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("count", 0);
            editor.commit();
            updateCount();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
