package ru.karpyzin.cepka.mvvm.hint

import android.app.Application
import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.karpyzin.cepka.base.BaseViewModel
import ru.karpyzin.domain.hint.HintUseCase

class HintViewModel @ViewModelInject constructor(
    application: Application,
    private val hintUseCase: HintUseCase
) : BaseViewModel(application) {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            hintUseCase.read(0)
        }
    }
}