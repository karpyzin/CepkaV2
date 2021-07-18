package ru.karpyzin.cepka.navigation

import ru.karpyzin.cepka.mvvm.home.HomeFragment
import ru.karpyzin.cepka.mvvm.subscriptions.SubscriptionsFragment
import ru.karpyzin.cepka.mvvm.week.WeekFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    object HomeFragmentScreen : SupportAppScreen() {
        override fun getFragment() = HomeFragment()
    }

    object WeekFragmentScreen : SupportAppScreen() {
        override fun getFragment() = WeekFragment()
    }

    object SubscriptionsFragmentScreen : SupportAppScreen() {
        override fun getFragment() = SubscriptionsFragment()
    }
}