package ir.reservs.reservs;

import org.junit.Test;

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
    public void phoneValidate_isCorrect(){
        assertTrue(CommonUtils.isPhoneValid("09168865663"));
    }
}