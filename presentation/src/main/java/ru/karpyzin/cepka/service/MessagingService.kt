package ru.karpyzin.cepka.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import ru.karpyzin.cepka.notifications.CloudMessagingManager
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var cloudMessagingManager: CloudMessagingManager

    override fun onNewToken(p0: String) {
        Timber.e("new token $p0")
        cloudMessagingManager.onNewToken(p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        Timber.e("asdasdasd")
        cloudMessagingManager.onMessageReceived(p0)
    }
}