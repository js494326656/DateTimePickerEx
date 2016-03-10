# DateTimePickerEx
DateTimePickerEx is an extension of DatePicker and TimePicker for Android.

![sample_anim](https://github.com/recruit-mtl/DateTimePickerEx/blob/master/DateTimePickerSampleCapture.png)

# Usage
layout.xml
```
        <jp.co.recruit.mtl.datetimepickerex.DatePickerEx
            android:id="@+id/event_date_picker"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:calendarViewShown="false"
            android:datePickerMode="spinner"  />

        <jp.co.recruit.mtl.datetimepickerex.TimePickerEx
            android:id="@+id/event_time_picker"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:timePickerMode="spinner" />
```
Java
```java
    private EventDatePickerListener mEventDatePickerListener;
    
    final DatePickerEx datePicker = (DatePickerEx) this.findViewById(R.id.event_date_picker);
    datePicker.init(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
            if (mEventDatePickerListener != null) {
                mEventDatePickerListener.onDateChanged(year, month, day, timePicker.getCurrentHour(), timePicker.getCurrentMinute());
            }
        }
    });
        
    final TimePickerEx timePicker = (TimePickerEx) this.findViewById(R.id.event_time_picker);
    timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
        @Override
        public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
            if (mEventDatePickerListener != null) {
                mEventDatePickerListener.onDateChanged(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), hour, minute);
            }
        }
    });
```

# Download
clone or copy [this file](https://github.com/recruit-mtl/DateTimePickerEx/blob/master/library/src/main/java/jp/co/recruit/mtl/datetimepickerex)

# License
DateTimePickerEx is available under the MIT license. See the LICENSE file for more info.

