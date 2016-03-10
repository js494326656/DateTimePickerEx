package jp.co.recruit.mtl.datetimepickerex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private EventDatePickerView mEventDatePickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEventDatePickerView = new EventDatePickerView(this.getApplicationContext(), null);

        Calendar calendar = Calendar.getInstance();
        mEventDatePickerView.setCurrentDate(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE)
        );
        setContentView(mEventDatePickerView);

    }
}
