package ir.reservs.reservs.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Objects;

public class AppPreferencesHelper implements PreferencesHelper {
    private static final String USER_ID = "PREF_KEY_USER_ID";
    private static final String USER_PHONE = "PREF_KEY_USER_PHONE";
    private static final String USER_NAME = "PREF_KEY_USER_NAME";
    private static final String USER_IMAGE = "PREF_KEY_USER_IMAGE";
    private static final String ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";


    private final SharedPreferences mPrefs;

    public AppPreferencesHelper(Context context) {
        mPrefs = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
    }

    @Override
    public Long getCurrentUserId() {
        long userId = mPrefs.getLong(USER_ID, -1L);
        return userId == -1L ? null : userId;
    }

    @Override
    public void setCurrentUserId(Long userId) {
        long id = userId == null ? -1L : userId;
        mPrefs.edit().putLong(USER_ID, id).apply();
    }

    @Override
    public String getCurrentUserPhone() {
        String userPhone = mPrefs.getString(USER_PHONE, "-1");
        return Objects.equals(userPhone, "-1") ? null : userPhone;
    }

    @Override
    public void setCurrentUserPhone(String phone) {
        mPrefs.edit().putString(USER_PHONE, phone).apply();
    }

    @Override
    public String getCurrentUserName() {
        String userName = mPrefs.getString(USER_NAME, "-1");
        return Objects.equals(userName, "-1") ? null : userName;
    }

    @Override
    public void setCurrentUserName(String name) {
        mPrefs.edit().putString(USER_NAME, name).apply();
    }

    @Override
    public void setCurrentUserImage(String image) {
        mPrefs.edit().putString(USER_IMAGE, image).apply();
    }

    @Override
    public String getCurrentUserImage() {
        String userImage = mPrefs.getString(USER_IMAGE, "-1");
        return Objects.equals(userImage, "-1") ? null : userImage;
    }

    @Override
    public String getAccessToken() {
        String accessToken = mPrefs.getString(ACCESS_TOKEN, "-1");
        return Objects.equals(accessToken, "-1") ? null : accessToken;
    }

    @Override
    public void setAccessToken(String token) {
        mPrefs.edit().putString(ACCESS_TOKEN, token).apply();
    }

    @Override
    public void removeAccessToken() {
        setAccessToken(null);
    }
}
