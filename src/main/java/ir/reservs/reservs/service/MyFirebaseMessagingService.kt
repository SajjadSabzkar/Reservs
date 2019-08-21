package ir.reservs.reservs.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ir.reservs.reservs.data.DataManager
import javax.inject.Inject

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(removeMessage: RemoteMessage) {
        super.onMessageReceived(removeMessage)
    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}