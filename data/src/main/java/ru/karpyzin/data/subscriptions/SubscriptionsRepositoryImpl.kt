package ru.karpyzin.data.subscriptions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.karpyzin.data.db.AppDatabase
import ru.karpyzin.domain.subscriptions.SubscriptionModel
import ru.karpyzin.domain.subscriptions.SubscriptionsRepository
import javax.inject.Inject

class SubscriptionsRepositoryImpl @Inject constructor(
    appDatabase: AppDatabase,
    private val subscriptionEntityMapper: SubscriptionEntityMapper
) : SubscriptionsRepository {
    private val dao = appDatabase.subscriptionsDao()

    override fun getAllFlow(): Flow<List<SubscriptionModel>> {
        return dao.getAllFlow().map { it.map { item -> subscriptionEntityMapper.fromEntity(item) } }
    }
}