package ru.karpyzin.cepka.mvvm.home

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import ru.karpyzin.cepka.base.BaseViewModel

class HomeViewModel @ViewModelInject constructor(
    application: Application
) : BaseViewModel(application) {

    fun init() {

    }

}