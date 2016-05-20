package com.mathieu_fontenille.hyperconnected;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeeklyFragment extends Fragment {
    TextView counter;
    SharedPreferences prefs;

    protected void updateCount()
    {
        Integer sharedCount = prefs.getInt("weeklyCount", 0);
        if (sharedCount == 0)
            counter.setText("0");
        else
            counter.setText(Integer.toString(sharedCount));
    }

    public WeeklyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        prefs = getContext().getSharedPreferences("com.mathieu_fontenille.hyperconnected", Context.MODE_PRIVATE);
        counter = (TextView)  getView().findViewById(R.id.count);
        updateCount();
        return inflater.inflate(R.layout.fragment_weekly, container, false);
    }

    public void onResume() {
        super.onResume();
        updateCount();
    }

}
