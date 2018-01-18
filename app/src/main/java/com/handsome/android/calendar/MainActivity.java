package com.handsome.android.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mCalendarView)
    CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Calendar calendar = Calendar.getInstance();
        mCalendarView.setTime(calendar.get(Calendar.YEAR), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY));
    }


}
