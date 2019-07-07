package ir.reservs.reservs.data;

import java.util.List;

import io.reactivex.Single;
import ir.reservs.reservs.data.network.ApiHelper;
import ir.reservs.reservs.data.prefs.PreferencesHelper;
import ir.reservs.reservs.model.ChangePassword;
import ir.reservs.reservs.model.User;
import ir.reservs.reservs.model.ReserveHistory;
import ir.reservs.reservs.model.Salon;
import ir.reservs.reservs.model.Success;
import ir.reservs.reservs.model.Time;

public class AppDataManager implements DataManager {
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    public AppDataManager(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        this.mPreferencesHelper = preferencesHelper;
        this.mApiHelper = apiHelper;
    }


    @Override
    public Single<User> register(String name, String phone, String password) {
        return mApiHelper.register(name, phone, password);
    }

    @Override
    public Single<User> login(String phone, String password) {
        return mApiHelper.login(phone, password);
    }

    @Override
    public Single<List<ReserveHistory>> reserves() {
        return mApiHelper.reserves();
    }

    @Override
    public Single<Success> reserve(String time_id, Integer salon_id, String authority, String date) {
        return mApiHelper.reserve(time_id, salon_id, authority, date);
    }

    @Override
    public Single<Success> reserveUpdate(String authority) {
        return mApiHelper.reserveUpdate(authority);
    }


    @Override
    public Single<List<Salon>> salons() {
        return mApiHelper.salons();
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public String getCurrentUserPhone() {
        return mPreferencesHelper.getCurrentUserPhone();
    }

    @Override
    public void setCurrentUserPhone(String phone) {
        mPreferencesHelper.setCurrentUserPhone(phone);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String name) {
        mPreferencesHelper.setCurrentUserName(name);
    }

    @Override
    public void setCurrentUserImage(String image) {
        mPreferencesHelper.setCurrentUserImage(image);
    }

    @Override
    public String getCurrentUserImage() {
        return mPreferencesHelper.getCurrentUserImage();
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String token) {
        mPreferencesHelper.setAccessToken(token);
    }

    @Override
    public void removeAccessToken() {
        mPreferencesHelper.removeAccessToken();
    }

    @Override
    public Single<Success> updateName(String name) {
        return mApiHelper.updateName(name);
    }

    @Override
    public Single<ChangePassword> updatePassword(String current_password, String new_password) {
        return mApiHelper.updatePassword(current_password, new_password);
    }

    @Override
    public Single<List<Time>> times(int salon_id, String date) {
        return mApiHelper.times(salon_id, date);
    }
}
