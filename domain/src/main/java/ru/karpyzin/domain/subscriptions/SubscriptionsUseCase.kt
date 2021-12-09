package ru.karpyzin.domain.subscriptions

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SubscriptionsUseCase {
    val subscriptions: Flow<List<SubscriptionModel>>
}

class SubscriptionsUseCaseImpl @Inject constructor(
    private val subscriptionsRepository: SubscriptionsRepository
) : SubscriptionsUseCase {

    override val subscriptions: Flow<List<SubscriptionModel>>
        get() = subscriptionsRepository.getAllFlow()

}