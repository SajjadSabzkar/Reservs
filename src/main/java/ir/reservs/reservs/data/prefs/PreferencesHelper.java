package ir.reservs.reservs.data.prefs;

public interface PreferencesHelper {
    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    String getCurrentUserPhone();

    void setCurrentUserPhone(String phone);

    String getCurrentUserName();

    void setCurrentUserName(String name);

    String getAccessToken();

    void setAccessToken(String token);

    void removeAccessToken();
}
