package ru.karpyzin.cepka.mvvm.signin

import android.app.Application
import android.text.format.DateUtils
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.karpyzin.cepka.base.BaseViewModel
import ru.karpyzin.cepka.view.widgets.InAppMessage
import ru.karpyzin.domain.account.AccountUseCase
import ru.karpyzin.domain.reminders.RemindersUseCase
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class SignInViewModel @ViewModelInject constructor(
    application: Application,
    private val accountUseCase: AccountUseCase
) : BaseViewModel(application) {

    fun onAuthClick(email: String, pass: String, signUp: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            if (signUp) accountUseCase.register(email, pass) else accountUseCase.auth(email, pass)
            inAppMessage.emit(InAppMessage("Cool \uD83D\uDC4D", "Try all functions!"))
            backClick.emit(true)
        }.onFailure {
            inAppMessage.emit(InAppMessage("Oops... \uD83D\uDE2C", "Something went wrong!"))
        }
    }

}