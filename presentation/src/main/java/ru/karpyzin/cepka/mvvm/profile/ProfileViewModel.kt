package ru.karpyzin.cepka.mvvm.profile

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.karpyzin.cepka.base.BaseViewModel
import ru.karpyzin.cepka.view.widgets.InAppMessage
import ru.karpyzin.domain.account.AccountUseCase

class ProfileViewModel @ViewModelInject constructor(
    application: Application,
    private val accountUseCase: AccountUseCase
) : BaseViewModel(application) {

    val accountFlow = accountUseCase.flow

    fun onLogoutClick() = viewModelScope.launch(Dispatchers.IO) {
        accountUseCase.logout()
        backClick.emit(true)
    }

    fun updateName(name: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (name.isNullOrEmpty()) {
                inAppMessage.emit(InAppMessage(summary = "Fill name field"))
                return@launch
            }

            accountUseCase.updateName(name)
        }
    }
}