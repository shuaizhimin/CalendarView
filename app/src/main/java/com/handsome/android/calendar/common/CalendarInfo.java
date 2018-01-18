package com.handsome.android.calendar.common;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * create by shuaizhimin on 2018-01-17 17:20
 */

public class CalendarInfo implements Parcelable {
    private int year, month, day;
    private CalendarMode mode;
    private Rect rect, borderRect;

    public CalendarInfo(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public CalendarInfo(int year, int month, int day, CalendarMode mode) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.mode = mode;
    }

    public CalendarInfo(Rect rect, int year, int month, int day, CalendarMode mode) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.mode = mode;
        this.rect = new Rect(rect);
    }

    public CalendarInfo(Rect rect, Rect borderRect, int year, int month, int day, CalendarMode mode) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.mode = mode;
        this.rect = new Rect(rect);
        this.borderRect = new Rect(borderRect);
    }


    public String getString() {
        switch (mode) {
            case YEAR:
                return "" + year;
            case MONTH:
                return year + "_" + month;
            case WEEK:
                return year + "_" + month + "_" + day;
            case DAY:
                return year + "_" + month + "_" + day + "_data";
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.year);
        dest.writeInt(this.month);
        dest.writeInt(this.day);
    }

    public CalendarInfo() {
    }

    protected CalendarInfo(Parcel in) {
        this.year = in.readInt();
        this.month = in.readInt();
        this.day = in.readInt();
    }

    public static final Parcelable.Creator<CalendarInfo> CREATOR = new Parcelable.Creator<CalendarInfo>() {
        @Override
        public CalendarInfo createFromParcel(Parcel source) {
            return new CalendarInfo(source);
        }

        @Override
        public CalendarInfo[] newArray(int size) {
            return new CalendarInfo[size];
        }
    };


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public CalendarMode getMode() {
        return mode;
    }

    public void setMode(CalendarMode mode) {
        this.mode = mode;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }
}
