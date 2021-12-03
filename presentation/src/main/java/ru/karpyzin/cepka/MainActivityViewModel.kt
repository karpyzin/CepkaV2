package ru.karpyzin.cepka

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import ru.karpyzin.cepka.base.BaseViewModel

class MainActivityViewModel @ViewModelInject constructor(
    application: Application
) : BaseViewModel(application) {
}