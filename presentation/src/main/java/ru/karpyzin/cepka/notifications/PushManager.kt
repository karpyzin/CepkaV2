package ru.karpyzin.cepka.notifications

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.karpyzin.cepka.view.widgets.InAppMessage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PushManager @Inject constructor(
    private val coroutineScope: CoroutineScope
) {

    val message = MutableSharedFlow<InAppMessage>()

    fun createPush(data: CloudMessagingManager.ContentMessage) = with(data) {
        if (summary == null && title == null) return@with

        coroutineScope.launch {
            message.emit(InAppMessage(title, summary))
        }
    }
}