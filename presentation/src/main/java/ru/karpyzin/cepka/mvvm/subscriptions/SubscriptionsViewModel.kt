package ru.karpyzin.cepka.mvvm.subscriptions

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import kotlinx.coroutines.flow.combine
import ru.karpyzin.cepka.base.BaseViewModel
import ru.karpyzin.cepka.view.listitems.SubscriptionListItem
import ru.karpyzin.domain.subscriptions.SubscriptionsUseCase

class SubscriptionsViewModel @ViewModelInject constructor(
    application: Application,
    private val subscriptionsUseCase: SubscriptionsUseCase
) : BaseViewModel(application) {

    private val subscriptionsFlow = subscriptionsUseCase.subscriptions

    val itemsFlow = combine(subscriptionsFlow) {
        it.first().map { item -> SubscriptionListItem(item) }
    }

}