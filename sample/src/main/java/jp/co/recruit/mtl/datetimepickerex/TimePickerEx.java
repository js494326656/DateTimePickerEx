package jp.co.recruit.mtl.datetimepickerex;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.lang.reflect.Field;

public class TimePickerEx  extends TimePicker {

    public TimePickerEx(Context context, AttributeSet attrs) {
        super(context, attrs);

        Class<?> internalRID = null;
        try {
            internalRID = Class.forName("com.android.internal.R$id");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (internalRID == null)
            return;

        Field hour = null;
        try {
            hour = internalRID.getField("hour");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        NumberPicker npHour = null;
        if (hour != null) {
            try {
                npHour = (NumberPicker) findViewById(hour.getInt(null));
                // NumberPicker の区切り線の色を設定する
                EventDatePickerView.setNumberPickerDividerColor(npHour,
                        ContextCompat.getColor(getContext(), R.color.number_picker_divider_color));
                // NumberPicker のテキストサイズを設定する
                EventDatePickerView.setNumberPickerTextSize(npHour);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Field divider = null;
        try {
            divider = internalRID.getField("divider");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        TextView tvDivider = null;
        if (divider != null) {
            try {
                tvDivider = (TextView) findViewById(divider.getInt(null));
                tvDivider.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        context.getResources().getDimension(R.dimen.number_picker_text_size));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Field minute = null;
        try {
            minute = internalRID.getField("minute");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        NumberPicker npMinute = null;
        if (minute != null) {
            try {
                npMinute = (NumberPicker) findViewById(minute.getInt(null));
                // NumberPicker の区切り線の色を設定する
                EventDatePickerView.setNumberPickerDividerColor(npMinute,
                        ContextCompat.getColor(getContext(), R.color.number_picker_divider_color));
                // NumberPicker のテキストサイズを設定する
                EventDatePickerView.setNumberPickerTextSize(npMinute);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Field amPm = null;
        try {
            amPm = internalRID.getField("amPm");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (amPm != null) {
            NumberPicker npAmPm = null;
            try {
                npAmPm = (NumberPicker) findViewById(amPm.getInt(null));
                // NumberPicker の区切り線の色を設定する
                EventDatePickerView.setNumberPickerDividerColor(npAmPm,
                        ContextCompat.getColor(getContext(), R.color.number_picker_divider_color));
                // NumberPicker のテキストサイズを設定する
                EventDatePickerView.setNumberPickerTextSize(npAmPm);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCurrentHour(int hour) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.setHour(hour);
        } else {
            super.setCurrentHour(hour);
        }
    }

    public void setCurrentMinute(int minute) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            super.setMinute(minute);
        else
            super.setCurrentMinute(minute);
    }

    public Integer getCurrentHour() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return super.getHour();
        } else {
            return super.getCurrentHour();
        }
    }

    public Integer getCurrentMinute() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return super.getMinute();
        } else {
            return super.getCurrentMinute();
        }
    }
}


