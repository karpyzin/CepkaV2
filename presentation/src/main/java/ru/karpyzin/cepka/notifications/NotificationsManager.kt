package ru.karpyzin.cepka.notifications

import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class NotificationsManager @Inject constructor(
    private val coroutineScope: CoroutineScope
) {
    fun createNotification(data: CloudMessagingManager.ContentMessage) {
        // todo
    }
}