package ru.karpyzin.cepka.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseViewModel(
    private val application: Application
) : ViewModel() {

    val openScreen = MutableSharedFlow<Int>()

    protected val context: Context
        get() = application.applicationContext
}