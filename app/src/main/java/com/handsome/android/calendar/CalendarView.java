package com.handsome.android.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import com.handsome.android.calendar.common.CalendarInfo;
import com.handsome.android.calendar.common.CalendarMode;
import com.handsome.android.calendar.layer.WeekBarLayer;
import com.handsome.android.calendar.layer.YearBarLayer;

import java.util.Calendar;

/**
 * create by shuaizhimin on 2018-01-17 17:07
 */

public class CalendarView extends View{

    protected YearBarLayer mYearBarLayer;    //年月
    protected WeekBarLayer mWeekBarLayer;    //星期
    protected CalendarInfo today;
    private boolean mIsMeasured;

    public CalendarView(Context context) {
        this(context,null);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
            getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    if (!mIsMeasured) {
                        mIsMeasured = true;
                        initLayer();
                    }
                    return mIsMeasured;
                }
            });
    }

    public void setTime(int year, int month, int day) {
        today = new CalendarInfo(year, month, day, CalendarMode.MONTH);
    }

    private void initLayer(){
        Calendar calendar=Calendar.getInstance();
        today = new CalendarInfo(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_YEAR));
        int mYear = today.getYear();
        int mMonth = today.getMonth();
        int mDay = today.getDay();
        int yearBarHeight = getResources().getDimensionPixelOffset(R.dimen.cal_weekbar_year_height);
        Rect yearBarRect = new Rect(0, 0, getWidth(), yearBarHeight);
        CalendarInfo yearBarInfo = new CalendarInfo(yearBarRect, mYear, mMonth, 1, CalendarMode.YEAR);
        mYearBarLayer = new YearBarLayer(getContext(),yearBarInfo);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mYearBarLayer != null) mYearBarLayer.onDraw(canvas);
    }
}
