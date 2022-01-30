package ru.karpyzin.cepka.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.karpyzin.cepka.view.widgets.InAppMessage

abstract class BaseViewModel(
    private val application: Application
) : ViewModel() {

    val openScreen = MutableSharedFlow<Int>()
    val backClick = MutableSharedFlow<Boolean>()
    val inAppMessage = MutableSharedFlow<InAppMessage>()

    protected val context: Context
        get() = application.applicationContext
}