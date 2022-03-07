package ru.karpyzin.cepka.notifications

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PushManager @Inject constructor() {
    data class ContentPush(
        val title: String,
        val summary: String
    )

    val message = MutableSharedFlow<ContentPush>()
}