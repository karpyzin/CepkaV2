package ru.karpyzin.cepka.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel

abstract class BaseViewModel(
    private val application: Application
) : ViewModel() {

    protected val context: Context
        get() = application.applicationContext
}