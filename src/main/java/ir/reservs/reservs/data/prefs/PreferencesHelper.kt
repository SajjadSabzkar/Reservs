package ir.reservs.reservs.data.prefs

interface PreferencesHelper {
    fun getCurrentUserId(): Long?

    fun setCurrentUserId(userId: Long?)

    fun getCurrentUserPhone(): String

    fun setCurrentUserPhone(phone: String)

    fun getCurrentUserName(): String

    fun setCurrentUserName(name: String)

    fun setCurrentUserImage(image: String)

    fun getCurrentUserImage(): String

    fun getAccessToken(): String?

    fun setAccessToken(token: String?)

    fun removeAccessToken()

}
