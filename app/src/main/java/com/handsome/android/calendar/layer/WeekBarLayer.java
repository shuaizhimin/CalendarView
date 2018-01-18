package com.handsome.android.calendar.layer;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.handsome.android.calendar.common.CalendarInfo;


/**
 * create by shuaizhimin on 2018-01-17 17:28
 */

public class WeekBarLayer implements CalendarLayer{
    private static final String BASE_WEEK[] = {"一", "二", "三", "四", "五", "六", "日"};




    @Override
    public Rect getBorderRect() {
        return null;
    }

    @Override
    public CalendarInfo getModeInfo() {
        return null;
    }

    @Override
    public void onDraw(Canvas canvas) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void scrollBy(int dx, int dy) {

    }

    @Override
    public boolean isShow() {
        return false;
    }
}
