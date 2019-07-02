package ir.reservs.reservs;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.GregorianCalendar;

import ir.huri.jcal.JalaliCalendar;
import ir.reservs.reservs.utils.CommonUtils;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void phoneValidate_isCorrect() {
        assertTrue(CommonUtils.isPhoneValid("09168865663"));
    }

    @Test
    public void date_check() {
        System.out.println(toTwoDigit("1"));
        System.out.println(toTwoDigit("2"));
        System.out.println(toTwoDigit("3"));
        System.out.println(toTwoDigit("4"));
        System.out.println(toTwoDigit("5"));
        System.out.println(toTwoDigit("6"));
        System.out.println(toTwoDigit("7"));
        System.out.println(toTwoDigit("8"));
        System.out.println(toTwoDigit("9"));
        System.out.println(toTwoDigit("10"));
        System.out.println(toTwoDigit("11"));
        System.out.println(toTwoDigit("12"));
        System.out.println(toTwoDigit("13"));
    }

    public String toTwoDigit(String num) {
        if (Integer.parseInt(num) < 10) {
            return "0" + num;
        }
        return num;
    }
}