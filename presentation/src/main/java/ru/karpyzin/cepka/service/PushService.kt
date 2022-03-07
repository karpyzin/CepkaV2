package ru.karpyzin.cepka.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.karpyzin.cepka.notifications.PushManager
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class PushService : FirebaseMessagingService() {

    @Inject
    lateinit var pushManager: PushManager

    @Inject
    lateinit var coroutineScope: CoroutineScope

    override fun onNewToken(p0: String) {
        Timber.e("asdad!")
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        val title = p0.notification?.title
        val body = p0.notification?.body
        Timber.e("Ща разберемся")
        Timber.e("body ${p0.notification?.body}")
        Timber.e("sdad ${p0.from}")
        Timber.e("asdasd ${p0.notification?.title}")

        if (title == null || body == null) return

        coroutineScope.launch {
            pushManager.message.emit(PushManager.ContentPush(title, body))
        }
    }

    override fun onMessageSent(p0: String) {
        Timber.e("NEW MESSAGE $p0")
    }
}