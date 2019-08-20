package ir.reservs.reservs.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    private static final String TAG = "CommonUtils";

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isPhoneValid(String phone) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "09[0-9]{9}";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static String moneyDisplayFormat(String money) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(Long.valueOf(money))+" تومان";
    }
    public static String moneyDisplayFormat(String money,int type) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        if(type==1) {
            return formatter.format(Long.valueOf(money)) + " تومان";
        }
        return formatter.format(Long.valueOf(money));
    }
}
