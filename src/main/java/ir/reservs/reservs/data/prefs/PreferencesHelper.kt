package ir.reservs.reservs.data.prefs

interface PreferencesHelper {
    fun getCurrentUserId(): Long?

    fun setCurrentUserId(userId: Long?)

    fun getCurrentUserPhone(): String

    fun setCurrentUserPhone(phone: String?)

    fun getCurrentUserName(): String

    fun setCurrentUserName(name: String?)

    fun setCurrentUserAge(age: String?)

    fun setCurrentUserImage(image: String?)

    fun getCurrentUserImage(): String

    fun getCurrentUserAge(): String

    fun getAccessToken(): String?

    fun setAccessToken(token: String?)

    fun logout()

    fun setIsVerify(status: Boolean)

    fun getVerify(): Boolean

    fun setUserBirthday(birthday: String)

    fun getUserBirthday(): String?

    fun setCredit(amount: Long)

    fun getCredit(): Long
}
