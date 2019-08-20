package ir.reservs.reservs.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.data.network.ApiHelper
import ir.reservs.reservs.data.prefs.PreferencesHelper
import ir.reservs.reservs.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AppDataManager(private val mPreferencesHelper: PreferencesHelper, private val mApiHelper: ApiHelper) : DataManager {

    override fun updateAvatar( avatar: MultipartBody.Part): Single<Avatar> {
        return config(mApiHelper.updateAvatar(avatar))
    }

    override fun updateFcmToken(fcmToken: String): Single<Success> {
        return config(mApiHelper.updateFcmToken(fcmToken))
    }

    override fun register(name: String, phone: String, password: String, fcmToken: String): Single<User> {
        return config(mApiHelper.register(name, phone, password, fcmToken))
    }

    override fun login(phone: String, password: String, fcmToken: String): Single<User> {
        return config(mApiHelper.login(phone, password, fcmToken))
    }

    override fun reserves(page: Int): Single<MutableList<History>> {
        return config(mApiHelper.reserves(page))
    }

    override fun reserve(time_id: String, salon_id: Int?, callBackUrl: String, date: String): Single<Payment> {
        return config(mApiHelper.reserve(time_id, salon_id, callBackUrl, date))
    }

    override fun reserveUpdate(authority: String): Single<Success> {
        return config(mApiHelper.reserveUpdate(authority))
    }


    override fun salons(): Single<MutableList<Salon>> {
        return config(mApiHelper.salons())
    }

    override fun salons(cityId: Int): Single<MutableList<Salon>> {
        return config(mApiHelper.salons(cityId))
    }

    override fun getCurrentUserId(): Long? {
        return mPreferencesHelper.getCurrentUserId()
    }

    override fun setCurrentUserId(userId: Long?) {
        mPreferencesHelper.setCurrentUserId(userId)
    }

    override fun getCurrentUserPhone(): String {
        return mPreferencesHelper.getCurrentUserPhone()
    }

    override fun setCurrentUserPhone(phone: String) {
        mPreferencesHelper.setCurrentUserPhone(phone)
    }

    override fun getCurrentUserName(): String {
        return mPreferencesHelper.getCurrentUserName()
    }

    override fun setCurrentUserName(name: String) {
        mPreferencesHelper.setCurrentUserName(name)
    }

    override fun setCurrentUserImage(image: String) {
        mPreferencesHelper.setCurrentUserImage(image)
    }

    override fun getCurrentUserImage(): String {
        return mPreferencesHelper.getCurrentUserImage()
    }

    override fun getAccessToken(): String? {
        return mPreferencesHelper.getAccessToken()
    }

    override fun setAccessToken(token: String?) {
        mPreferencesHelper.setAccessToken(token)
    }

    override fun removeAccessToken() {
        mPreferencesHelper.removeAccessToken()
    }

    override fun updateName(name: String): Single<Success> {
        return config(mApiHelper.updateName(name))
    }

    override fun updatePassword(current_password: String, new_password: String): Single<ChangePassword> {
        return config(mApiHelper.updatePassword(current_password, new_password))
    }

    override fun times(salon_id: Int, date: String): Single<MutableList<Time>> {
        return config(mApiHelper.times(salon_id, date))
    }

    override fun cities(): Single<MutableList<City>> {
        return config(mApiHelper.cities())
    }

    private fun <T : Any?> config(single: Single<T>): Single<T> {
        return single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}
