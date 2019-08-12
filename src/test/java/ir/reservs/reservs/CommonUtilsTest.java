package ir.reservs.reservs;

import org.junit.Test;

import ir.reservs.reservs.utils.CommonUtils;

public class CommonUtilsTest {
    @Test
    public void moneyDisplayFormatTest() {
//        System.out.println(CommonUtils.moneyDisplayFormat("0"));
        System.out.println(CommonUtils.moneyDisplayFormat("100"));
        System.out.println(CommonUtils.moneyDisplayFormat("150"));
        System.out.println(CommonUtils.moneyDisplayFormat("2000"));
        System.out.println(CommonUtils.moneyDisplayFormat("2500"));
        System.out.println(CommonUtils.moneyDisplayFormat("25000"));
        System.out.println(CommonUtils.moneyDisplayFormat("10550"));
        System.out.println(CommonUtils.moneyDisplayFormat("25500"));
        System.out.println(CommonUtils.moneyDisplayFormat("100000"));
    }
}
