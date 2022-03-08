package ru.karpyzin.cepka.mvvm.hint

import android.app.Application
import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.karpyzin.cepka.base.BaseViewModel
import ru.karpyzin.domain.hint.HintModel
import ru.karpyzin.domain.hint.HintUseCase

class HintViewModel @ViewModelInject constructor(
    application: Application,
    private val hintUseCase: HintUseCase
) : BaseViewModel(application) {

    val hintFlow = MutableSharedFlow<HintModel?>()

    fun loadHint(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            hintFlow.emit(hintUseCase.get(id))
            hintUseCase.read(id)
        }
    }
}