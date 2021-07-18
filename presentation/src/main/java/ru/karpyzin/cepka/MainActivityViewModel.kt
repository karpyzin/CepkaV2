package ru.karpyzin.cepka

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import ru.karpyzin.cepka.base.BaseViewModel
import ru.karpyzin.cepka.navigation.Screens
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MainActivityViewModel @ViewModelInject constructor(
    application: Application,
    private val router: Router
) : BaseViewModel(application) {

    fun init() {
        //router.newRootScreen(Screens.HomeFragmentScreen)
    }

    fun changeMainScreen(screen: SupportAppScreen) {
        router.replaceScreen(screen)
    }

}