package com.handsome.android.calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.handsome.android.calendar.horizontal.HorizontalActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mHorizontalCalendar)
    void goHorizontalCalendar() {
        startActivity(new Intent(this, HorizontalActivity.class));
    }
}
