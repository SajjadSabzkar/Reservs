package ir.reservs.reservs.data.prefs

import android.content.Context
import android.content.SharedPreferences

class AppPreferencesHelper(context: Context) : PreferencesHelper {


    private val mPrefs: SharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)

    override fun getCurrentUserId(): Long? {
        val userId = mPrefs.getLong(USER_ID, -1L)
        return if (userId == -1L) null else userId
    }

    override fun setCurrentUserId(userId: Long?) {
        val id = userId ?: -1L
        mPrefs.edit().putLong(USER_ID, id).apply()
    }

    override fun getCurrentUserPhone(): String? {
        val userPhone = mPrefs.getString(USER_PHONE, "-1")
        return if (userPhone == "-1") null else userPhone
    }

    override fun setCurrentUserPhone(phone: String) {
        mPrefs.edit().putString(USER_PHONE, phone).apply()
    }

    override fun getCurrentUserName(): String? {
        val userName = mPrefs.getString(USER_NAME, "-1")
        return if (userName == "-1") null else userName
    }

    override fun setCurrentUserName(name: String) {
        mPrefs.edit().putString(USER_NAME, name).apply()
    }

    override fun setCurrentUserImage(image: String) {
        mPrefs.edit().putString(USER_IMAGE, image).apply()
    }

    override fun getCurrentUserImage(): String? {
        val userImage = mPrefs.getString(USER_IMAGE, "-1")
        return if (userImage == "-1") null else userImage
    }

    override fun getAccessToken(): String? {
        val accessToken = mPrefs.getString(ACCESS_TOKEN, "-1")
        return if (accessToken == "-1") null else accessToken
    }

    override fun setAccessToken(token: String?) {
        mPrefs.edit().putString(ACCESS_TOKEN, token).apply()
    }

    override fun removeAccessToken() {
        accessToken = null
    }

    companion object {
        private val USER_ID = "PREF_KEY_USER_ID"
        private val USER_PHONE = "PREF_KEY_USER_PHONE"
        private val USER_NAME = "PREF_KEY_USER_NAME"
        private val USER_IMAGE = "PREF_KEY_USER_IMAGE"
        private val ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
    }
}
