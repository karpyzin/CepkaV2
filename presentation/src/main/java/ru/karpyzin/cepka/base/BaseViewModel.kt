package ru.karpyzin.cepka.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

abstract class BaseViewModel(
    private val application: Application
) : ViewModel() {

    protected val context: Context
        get() = application.applicationContext

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}