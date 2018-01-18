package com.handsome.android.calendar.layer;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.handsome.android.calendar.common.CalendarInfo;

/**
 * create by shuaizhimin on 2018-01-17 17:19
 */

public interface CalendarLayer {
    Rect getBorderRect();

    CalendarInfo getModeInfo();

    void onDraw(Canvas canvas);

    boolean onTouchEvent(MotionEvent event);

    void scrollBy(int dx, int dy);

    boolean isShow();
}
