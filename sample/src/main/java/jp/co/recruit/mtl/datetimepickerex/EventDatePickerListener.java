package jp.co.recruit.mtl.datetimepickerex;

import java.util.EventListener;

public interface EventDatePickerListener extends EventListener {
    abstract public void onDateChanged(int year, int monthOfYear, int dayOfMonth, int hour24, int minute);
}

