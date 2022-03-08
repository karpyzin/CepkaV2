package ru.karpyzin.cepka

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import ru.karpyzin.cepka.base.BaseViewModel
import ru.karpyzin.cepka.notifications.CloudMessagingManager
import ru.karpyzin.cepka.notifications.CloudMessagingManager.Companion.TOPIC_STORIES

class MainActivityViewModel @ViewModelInject constructor(
    application: Application,
    cloudMessagingManager: CloudMessagingManager
) : BaseViewModel(application) {

    init {
        cloudMessagingManager.subscribe(TOPIC_STORIES)
    }

}