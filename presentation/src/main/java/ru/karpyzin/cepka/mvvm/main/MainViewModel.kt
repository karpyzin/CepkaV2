package ru.karpyzin.cepka.mvvm.main

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import ru.karpyzin.cepka.base.BaseViewModel

class MainViewModel @ViewModelInject constructor(application: Application) : BaseViewModel(application) {
}