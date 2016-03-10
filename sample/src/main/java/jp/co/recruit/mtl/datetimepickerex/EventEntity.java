package jp.co.recruit.mtl.datetimepickerex;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EventEntity implements Serializable {

    public static final int MIN_YEAR = 1970;   // 最少範囲年
    public static final int MAX_YEAR = 2036;   // 最大範囲年

    // 最少範囲カレンダー取得
    public static Calendar getMinRangeCalendar() {
        Calendar rangeCalendar = new GregorianCalendar(MIN_YEAR, 0, 1, 0, 0, 0);
        rangeCalendar.set(Calendar.MILLISECOND, 0);
        return rangeCalendar;
    }

    // 最大範囲カレンダー取得
    public static Calendar getMaxRangeCalendar() {
        Calendar rangeCalendar = new GregorianCalendar(MAX_YEAR, 12 -1, 31, 23, 59, 59);
        rangeCalendar.set(Calendar.MILLISECOND, 0);
        return rangeCalendar;
    }
}
