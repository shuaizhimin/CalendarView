package com.handsome.android.calendar.layer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;

import com.handsome.android.calendar.R;
import com.handsome.android.calendar.common.CalendarInfo;
import com.handsome.android.calendar.common.CalendarMode;

import java.util.Locale;


/**
 * create by shuaizhimin on 2018-01-17 17:18
 */

public class YearBarLayer implements CalendarLayer {
    private static final String YEAR = "年";
    private static final String MONTH = "月";
    private static final String TODAY = "今";

    private Paint mPaint;                           //画笔
    private Rect mRect;                             //绘制区域
    private Rect mYearRect;                         //年月绘制区域
    private Rect mLeftArrowRect;
    private Rect mLeftBitmapArrowRect;
    private Rect mRightArrowRect;
    private Rect mRightBitmapArrowRect;

    private int mYearAreaColor = Color.WHITE;       //年份导航条的背景色
    private int mYearTextColor = Color.BLACK;       //年份文字颜色

    private Bitmap mLeftArrowBitmap;
    private Bitmap mRightArrowBitmap;

    private boolean mIsShow = true;
    private String mYearString;                     //年月的字符串
    private int mYearTextSize = 16;                 //年月文字大小
    private float mYearItemWidth;
    private float mYearTextHeight;
    private float mYearTextAscent;

    private int mYearPadding = 10;                  //年与左右箭头的间距,单位 dp
    private int mArrowRectSize = 20;                //箭头的大小,单位 dp

    private int mDividerColor = 0xFFE6E6E6;         //分割线的颜色
    private float mDividerHeight = 0.5f;            //分割线的高度,单位 dp

    public YearBarLayer(Context context, CalendarInfo info) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        mYearTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mYearTextSize, metrics);
        mYearPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mYearPadding, metrics);
        mArrowRectSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mArrowRectSize, metrics);
        mDividerHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mDividerHeight, metrics);

        mLeftArrowBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.calendar_left_arrow);
        mRightArrowBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.calendar_right_arrow);

        mRect = info.getRect();
        mYearRect = new Rect();
        mLeftArrowRect = new Rect();
        mLeftBitmapArrowRect = new Rect();
        mRightArrowRect = new Rect();
        mRightBitmapArrowRect = new Rect();
        init(info);
    }

    private void init(CalendarInfo info) {
        CalendarMode mode = info.getMode();
        int year = info.getYear();
        int month = info.getMonth();
        if (mode == CalendarMode.YEAR) {
            mYearString = year + YEAR;
        } else {
            month += month;
            String monthStr = String.format(Locale.getDefault(), "%02d", month);
            mYearString = year + YEAR + monthStr + MONTH;
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mPaint.setTextSize(mYearTextSize);
        Paint.FontMetrics textFontMetrics = mPaint.getFontMetrics();
        mYearTextHeight = textFontMetrics.descent - textFontMetrics.ascent;
        mYearTextAscent = textFontMetrics.ascent;
        mYearItemWidth = mPaint.measureText(mYearString);

        mYearRect.top = mRect.top;
        mYearRect.left = (int) (mRect.centerX() - mYearItemWidth / 2);
        mYearRect.right = (int) (mRect.centerX() + mYearItemWidth / 2);
        mYearRect.bottom = mRect.bottom;

        mLeftArrowRect.left = mYearRect.left - mYearPadding - mArrowRectSize;
        mLeftArrowRect.right = mYearRect.left - mYearPadding;
        mLeftArrowRect.top = (mRect.top + mRect.bottom) / 2 - mArrowRectSize / 2;
        mLeftArrowRect.bottom = (mRect.top + mRect.bottom) / 2 + mArrowRectSize / 2;

        mLeftBitmapArrowRect.left = 0;
        mLeftBitmapArrowRect.top = 0;
        mLeftBitmapArrowRect.right = mLeftArrowBitmap.getWidth();
        mLeftBitmapArrowRect.bottom = mLeftArrowBitmap.getHeight();

        mRightArrowRect.left = mYearRect.right + mYearPadding;
        mRightArrowRect.top = (mRect.top + mRect.bottom) / 2 - mArrowRectSize / 2;
        mRightArrowRect.right = mYearRect.right + mYearPadding + mArrowRectSize;
        mRightArrowRect.bottom = (mRect.top + mRect.bottom) / 2 + mArrowRectSize / 2;


        mRightBitmapArrowRect.left = 0;
        mRightBitmapArrowRect.top = 0;
        mRightBitmapArrowRect.right = mRightArrowBitmap.getWidth();
        mRightBitmapArrowRect.bottom = mRightArrowBitmap.getHeight();
    }

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
        if (!isShow()) return;
        //绘制背景
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mYearAreaColor);
        canvas.drawRect(mRect, mPaint);
        //绘制箭头
        canvas.drawBitmap(mLeftArrowBitmap, mLeftBitmapArrowRect, mLeftArrowRect, mPaint);
        canvas.drawBitmap(mRightArrowBitmap, mRightBitmapArrowRect, mRightArrowRect, mPaint);

        //绘制文字
        mPaint.setColor(mYearTextColor);
        mPaint.setTextSize(mYearTextSize);
        float x = mYearRect.left + (mYearRect.width() - mYearItemWidth) / 2;
        float y = mYearRect.top + (mYearRect.height() - mYearTextHeight) / 2 - mYearTextAscent;
        canvas.drawText(mYearString, x, y, mPaint);

        //绘制年份与星期的分割线
        mPaint.setColor(mDividerColor);
        mPaint.setStrokeWidth(mDividerHeight);
        canvas.drawLine(mRect.left,mRect.bottom - mDividerHeight / 2,mRect.right,mRect.bottom,mPaint);


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
        return mIsShow;
    }
}
