package ir.reservs.reservs.data

import io.reactivex.Single
import ir.reservs.reservs.data.network.ApiHelper
import ir.reservs.reservs.data.prefs.PreferencesHelper
import ir.reservs.reservs.model.*

class AppDataManager(private val mPreferencesHelper: PreferencesHelper, private val mApiHelper: ApiHelper) : DataManager {

    override fun register(name: String, phone: String, password: String): Single<User> {
        return mApiHelper.register(name, phone, password)
    }

    override fun login(phone: String, password: String): Single<User> {
        return mApiHelper.login(phone, password)
    }

    override fun reserves(): Single<MutableList<ReserveHistory>> {
        return mApiHelper.reserves()
    }

    override fun reserve(time_id: String, salon_id: Int?, authority: String, date: String): Single<Success> {
        return mApiHelper.reserve(time_id, salon_id, authority, date)
    }

    override fun reserveUpdate(authority: String): Single<Success> {
        return mApiHelper.reserveUpdate(authority)
    }


    override fun salons(): Single<MutableList<Salon>> {
        return mApiHelper.salons()
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
        return mApiHelper.updateName(name)
    }

    override fun updatePassword(current_password: String, new_password: String): Single<ChangePassword> {
        return mApiHelper.updatePassword(current_password, new_password)
    }

    override fun times(salon_id: Int, date: String): Single<MutableList<Time>> {
        return mApiHelper.times(salon_id, date)
    }
}
