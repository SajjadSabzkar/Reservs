package ir.reservs.reservs.data.prefs

interface PreferencesHelper {
    var currentUserId: Long?

    var currentUserPhone: String

    var currentUserName: String

    var currentUserImage: String

    var accessToken: String

    fun removeAccessToken()
}
