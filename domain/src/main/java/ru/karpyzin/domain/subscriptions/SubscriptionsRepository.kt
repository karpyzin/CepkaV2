package ru.karpyzin.domain.subscriptions

import kotlinx.coroutines.flow.Flow

interface SubscriptionsRepository {
    fun getAllFlow(): Flow<List<SubscriptionModel>>
}