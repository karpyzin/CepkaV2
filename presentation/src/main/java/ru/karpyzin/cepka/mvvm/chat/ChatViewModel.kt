package ru.karpyzin.cepka.mvvm.chat

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.karpyzin.cepka.base.BaseViewModel
import ru.karpyzin.cepka.view.widgets.InAppMessage
import ru.karpyzin.domain.account.AccountUseCase

class ChatViewModel @ViewModelInject constructor(
    application: Application,

) : BaseViewModel(application) {

    private val messaging: FirebaseMessaging = FirebaseMessaging.getInstance().apply {
        subscribeToTopic("topic_story")
    }

    fun onMessageSend(text: String) {
        val msg = RemoteMessage.Builder("3hBEvt8uA2ct5stCo4iarpQfwA62")
            .addData("key_type", "type_notification")
            .addData("key_title", "sdfsdfsdfsdf33233")
            .addData("key_summary", text)
            .build()
        messaging.send(msg)
    }

}