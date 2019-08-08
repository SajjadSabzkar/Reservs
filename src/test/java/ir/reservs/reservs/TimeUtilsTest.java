package ir.reservs.reservs;

import org.junit.Test;

import java.util.GregorianCalendar;

import ir.huri.jcal.JalaliCalendar;
import ir.reservs.reservs.utils.TimeUtils;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TimeUtilsTest {

    @Test
    public void dateFormatTest() {
        assertEquals(TimeUtils.INSTANCE.dateFormat(1398, 5, 16), "1398/05/16");
    }

    @Test
    public void dateDisplayFormatTest() {
        String result = TimeUtils.INSTANCE.dateDisplayFormat(1398, 5, 16);
        assertEquals(result, "1398/05/16");

        result = TimeUtils.INSTANCE.dateDisplayFormat(1400, 1, 31);
        assertEquals(result, "1400/01/31");

        result = TimeUtils.INSTANCE.dateDisplayFormat(1400, 12, 29);
        assertEquals(result, "1400/12/29");
    }
    @Test
    public void toStringTest(){
        String result = TimeUtils.INSTANCE.toString("1:31");
        assertEquals(result,"1 ساعت و 31 دقیقه");
    }

    @Test
    public void diffTimesTest() {

        assertEquals(TimeUtils.INSTANCE.diff("08:00", "09:30"), "1:30");
        assertEquals(TimeUtils.INSTANCE.diff("09:30", "11:00"), "1:30");
        assertEquals(TimeUtils.INSTANCE.diff("23:59", "01:30"), "1:31");
        assertEquals(TimeUtils.INSTANCE.diff("23:30", "01:00"), "1:30");
    }

    @Test
    public void convertStringToDateTest() {
        JalaliCalendar jalaliCalendar = new JalaliCalendar(new GregorianCalendar());
        assertEquals(jalaliCalendar, TimeUtils.INSTANCE.convertStringToDate("1398-05-16"));
    }


    @Test
    public void date_check() {
        assertEquals(TimeUtils.INSTANCE.toTwoDigit(1), "01");
        assertEquals(TimeUtils.INSTANCE.toTwoDigit(2), "02");
        assertEquals(TimeUtils.INSTANCE.toTwoDigit(3), "03");
        assertEquals(TimeUtils.INSTANCE.toTwoDigit(4), "04");
        assertEquals(TimeUtils.INSTANCE.toTwoDigit(5), "05");
        assertEquals(TimeUtils.INSTANCE.toTwoDigit(6), "06");
        assertEquals(TimeUtils.INSTANCE.toTwoDigit(7), "07");
        assertEquals(TimeUtils.INSTANCE.toTwoDigit(8), "08");
        assertEquals(TimeUtils.INSTANCE.toTwoDigit(9), "09");
        assertEquals(TimeUtils.INSTANCE.toTwoDigit(10), "10");
        assertEquals(TimeUtils.INSTANCE.toTwoDigit(11), "11");
        assertEquals(TimeUtils.INSTANCE.toTwoDigit(12), "12");
        assertEquals(TimeUtils.INSTANCE.toTwoDigit(13), "13");
    }

}