package jp.co.recruit.mtl.datetimepickerex;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class EventDatePickerView extends FrameLayout {
    private static Context mContext;
    private EventDatePickerListener mEventDatePickerListener;

    public EventDatePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        // base layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main, this, false);
        this.addView(view);

        // date picker
        hideYear();

        // time picker
        TimePickerEx timePicker = (TimePickerEx) view.findViewById(R.id.event_time_picker);
        timePicker.setIs24HourView(true);
    }

    public void init(int year, int monthOfYear, int dayOfMonth, int hour, int minute, EventDatePickerListener listener) {
        this.mEventDatePickerListener = listener;
        final DatePickerEx datePicker = (DatePickerEx) this.findViewById(R.id.event_date_picker);
        final TimePickerEx timePicker = (TimePickerEx) this.findViewById(R.id.event_time_picker);

        datePicker.init(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
                if (mEventDatePickerListener != null) {
                    mEventDatePickerListener.onDateChanged(i, i2, i3, timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                }
            }
        });
        // 最少日付を設定
        datePicker.setMinDate(EventEntity.getMinRangeCalendar().getTimeInMillis());
        // 最大日付を設定
        datePicker.setMaxDate(EventEntity.getMaxRangeCalendar().getTimeInMillis());
        // DatePicker の区切り線の色を設定する
        setDatePickerDividerColor(datePicker);

        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                if (mEventDatePickerListener != null) {
                    mEventDatePickerListener.onDateChanged(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), i, i2);
                }
            }
        });
    }

    public void setCurrentDate(int year, int monthOfYear, int dayOfMonth, int hour, int minute) {
        this.init(year, monthOfYear, dayOfMonth, hour, minute, this.mEventDatePickerListener);
    }

    public void setMinDate(long time) {
        final DatePickerEx datePicker = (DatePickerEx) this.findViewById(R.id.event_date_picker);
        datePicker.setMinDate(time);
    }

    public void setEventDatePickerListener(EventDatePickerListener listener) {
        this.mEventDatePickerListener = listener;
        DatePickerEx datePicker = (DatePickerEx) this.findViewById(R.id.event_date_picker);
        TimePickerEx timePicker = (TimePickerEx) this.findViewById(R.id.event_time_picker);
        this.init(
                datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                timePicker.getCurrentHour(),
                timePicker.getCurrentMinute(),
                this.mEventDatePickerListener
        );
    }

    // フォーカスを切り替える
    public void switchRequestFocus() {
        DatePickerEx datePicker = (DatePickerEx) this.findViewById(R.id.event_date_picker);
        TimePickerEx timePicker = (TimePickerEx) this.findViewById(R.id.event_time_picker);
        if (datePicker != null)
            datePicker.requestFocus();
        if (timePicker != null)
            timePicker.requestFocus();
    }

    public void showYear() {
        Context context = this.getContext();
        int yearPickerId = context.getResources().getSystem().getIdentifier("year", "id", "android");
        View yearPicker = this.findViewById(yearPickerId);
        if (yearPicker != null) yearPicker.setVisibility(VISIBLE);
    }

    public void hideYear() {
        Context context = this.getContext();
        int yearPickerId = context.getResources().getSystem().getIdentifier("year", "id", "android");
        View yearPicker = this.findViewById(yearPickerId);
        if (yearPicker != null) yearPicker.setVisibility(GONE);
    }

    public void showTimePicker() {
        TimePickerEx timePicker = (TimePickerEx) this.findViewById(R.id.event_time_picker);
        timePicker.setVisibility(VISIBLE);
    }

    public void hideTimePicker() {
        TimePickerEx timePicker = (TimePickerEx) this.findViewById(R.id.event_time_picker);
        timePicker.setVisibility(GONE);
    }

    public void setAllDayMode(boolean allDayMode) {
        if (allDayMode) {
            this.showYear();
            this.hideTimePicker();
        } else {
            this.hideYear();
            this.showTimePicker();
        }
    }

    public Calendar getSetCalendar() {
        Calendar cal = Calendar.getInstance();
        DatePickerEx datePicker = (DatePickerEx) this.findViewById(R.id.event_date_picker);
        TimePickerEx timePicker = (TimePickerEx) this.findViewById(R.id.event_time_picker);
        cal.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), Calendar.HOUR_OF_DAY, Calendar.MINUTE, 0);
        return cal;
    }

    // DatePicker の区切り線の色を設定する
    public void setDatePickerDividerColor(final DatePicker picker) {
        if (picker == null || picker.getChildAt(0) == null)
            return;
        LinearLayout linear = (LinearLayout) picker.getChildAt(0);
        if (linear.getChildAt(0) == null)
            return;
        linear = (LinearLayout) linear.getChildAt(0);
        for (int i = 0; i < linear.getChildCount(); i++) {
            if (linear.getChildAt(i) != null && linear.getChildAt(i) instanceof NumberPicker) {
                // NumberPicker の区切り線の色を設定する
                setNumberPickerDividerColor((NumberPicker) linear.getChildAt(i),
                        ContextCompat.getColor(getContext(), R.color.number_picker_divider_color));
                // NumberPicker のテキストサイズを設定する
                setNumberPickerTextSize((NumberPicker) linear.getChildAt(i));
            }
        }
    }

    // NumberPicker の区切り線の色を設定する
    public static void setNumberPickerDividerColor(NumberPicker picker, int color) {
        if (picker == null)
            return;
        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    // NumberPicker のテキストサイズを設定する
    public static void setNumberPickerTextSize(final NumberPicker picker) {
        if (picker == null || mContext == null || mContext.getResources() == null)
            return;
        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        // EditText の数字
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mInputText")) {
                pf.setAccessible(true);
                try {
                    ((EditText) pf.get(picker)).setTextSize(TypedValue.COMPLEX_UNIT_PX,
                            mContext.getResources().getDimension(R.dimen.number_picker_text_size));
                    ((EditText) pf.get(picker)).setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        // スクロールの数字
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectorWheelPaint")) {
                pf.setAccessible(true);
                try {
                    ((Paint) pf.get(picker)).setTextSize(mContext.getResources().getDimensionPixelSize(R.dimen.number_picker_text_size));
                    ((Paint) pf.get(picker)).setColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

}
