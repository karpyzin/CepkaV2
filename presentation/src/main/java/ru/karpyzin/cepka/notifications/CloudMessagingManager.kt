package ru.karpyzin.cepka.notifications

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CloudMessagingManager @Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val pushManager: PushManager,
    private val notificationsManager: NotificationsManager
) {

    abstract class CloudMessage
    data class ContentMessage(
        val title: String?,
        val summary: String?
    ) : CloudMessage()

    companion object {
        const val KEY_TYPE = "key_type"
        const val KEY_ID = "key_id"
        const val KEY_TITLE = "key_title"
        const val KEY_SUMMARY = "key_summary"
        const val TYPE_NOTIFICATION = "type_notification"
        const val TYPE_STORY = "type_story"

        const val TOPIC_STORIES = "topic_story"
    }

    private val service = FirebaseMessaging.getInstance()

    fun onMessageReceived(data: RemoteMessage) {
        coroutineScope.launch(Dispatchers.IO) {
            val type = data.data[KEY_TYPE] ?: TYPE_NOTIFICATION

            when (type) {
                TYPE_NOTIFICATION -> {
                    val title = data.data[KEY_TITLE]
                    val summary = data.data[KEY_SUMMARY]
                    val content = ContentMessage(title, summary)

                    notificationsManager.createNotification(content)
                    pushManager.createPush(content)
                }
            }
        }
    }

    fun onNewToken(token: String) {

    }

    fun subscribe(type: String) {
        service.subscribeToTopic(type)
    }

    fun unsubscribe(type: String) {
        service.unsubscribeFromTopic(type)
    }
}