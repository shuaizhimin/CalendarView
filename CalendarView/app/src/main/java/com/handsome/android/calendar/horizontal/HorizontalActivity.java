package com.handsome.android.calendar.horizontal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.handsome.android.calendar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalActivity extends AppCompatActivity {
    @BindView(R.id.mYearMonthText)
    TextView mYearMonthText;   //年月现实
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontal_activity);
        ButterKnife.bind(this);
        initView();
    }
    private void initView(){


    }



}
