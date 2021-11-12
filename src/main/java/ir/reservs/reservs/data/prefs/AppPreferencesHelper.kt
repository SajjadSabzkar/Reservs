package ir.reservs.reservs.data.prefs

import android.content.Context
import android.content.SharedPreferences


class AppPreferencesHelper(context: Context) : PreferencesHelper {

    private val USER_ID = "PREF_KEY_USER_ID"
    private val USER_PHONE = "PREF_KEY_USER_PHONE"
    private val USER_NAME = "PREF_KEY_USER_NAME"
    private val USER_AGE = "PREF_KEY_USER_AGE"
    private val USER_IMAGE = "PREF_KEY_USER_IMAGE"
    private val USER_IS_VERIFY = "PREF_KEY_USER_IS_VERIFY"
    private val USER_BIRTHDAY = "PREF_KEY_USER_BIRTHDAY"
    private val ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
    private val USER_CREDIT = "PREF_KEY_USER_CREDIT"


    private var mPrefs: SharedPreferences = context.getSharedPreferences("user_pref",
            Context.MODE_PRIVATE)

    override fun getCurrentUserId(): Long? {
        val userId = mPrefs.getLong(USER_ID, -1L)
        return if (userId == -1L) null else userId
    }

    override fun setCurrentUserId(userId: Long?) {
        val id = userId ?: -1L
        mPrefs.edit().putLong(USER_ID, id).apply()
    }

    override fun getCurrentUserPhone(): String {
        return mPrefs.getString(USER_PHONE, "")!!
    }

    override fun setCurrentUserPhone(phone: String?) {
        mPrefs.edit().putString(USER_PHONE, phone).apply()
    }

    override fun getCurrentUserName(): String {
        return mPrefs.getString(USER_NAME, "")!!
    }

    override fun setCurrentUserName(name: String?) {
        mPrefs.edit().putString(USER_NAME, name).apply()
    }

    override fun setCurrentUserAge(age: String?) {
        mPrefs.edit().putString(USER_AGE, age).apply()
    }

    override fun setCurrentUserImage(image: String?) {
        mPrefs.edit().putString(USER_IMAGE, image).apply()
    }

    override fun getCurrentUserImage(): String {
        return mPrefs.getString(USER_IMAGE, "-1")!!
    }

    override fun getCurrentUserAge(): String {
        return mPrefs.getString(USER_AGE, "")!!
    }

    override fun getAccessToken(): String? {
        return mPrefs.getString(ACCESS_TOKEN, null)
    }

    override fun setAccessToken(token: String?) {
        mPrefs.edit().putString(ACCESS_TOKEN, token).apply()
    }

    override fun logout() {
        setAccessToken(null)
        setCurrentUserAge(null)
        setCurrentUserName(null)
        setCurrentUserImage(null)
        setCurrentUserId(null)
        setCurrentUserPhone(null)
    }

    override fun setIsVerify(status: Boolean) {
        mPrefs.edit().putBoolean(USER_IS_VERIFY, status).apply()
    }

    override fun getVerify(): Boolean {
        return mPrefs.getBoolean(USER_IS_VERIFY, false)
    }

    override fun setUserBirthday(birthday: String) {
        mPrefs.edit().putString(USER_BIRTHDAY, birthday).apply()
    }

    override fun getUserBirthday(): String? {
        return mPrefs.getString(USER_BIRTHDAY, null)

    }

    override fun setCredit(amount: Long) {
        mPrefs.edit().putLong(USER_CREDIT, amount).apply()
    }

    override fun getCredit(): Long {
        return mPrefs.getLong(USER_CREDIT, 0)
    }
}
