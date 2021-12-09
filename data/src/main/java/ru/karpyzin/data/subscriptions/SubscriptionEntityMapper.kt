package ru.karpyzin.data.subscriptions

import ru.karpyzin.data.base.EntityMapper
import ru.karpyzin.data.db.entity.SubscriptionEntity
import ru.karpyzin.domain.subscriptions.SubscriptionModel
import javax.inject.Inject

class SubscriptionEntityMapper @Inject constructor() : EntityMapper<SubscriptionEntity, SubscriptionModel> {
    override fun toEntity(data: SubscriptionModel): SubscriptionEntity = with(data) {
        SubscriptionEntity(id, name, description, price, currency, repeatType, repeatDay)
    }

    override fun fromEntity(data: SubscriptionEntity): SubscriptionModel = with(data) {
        SubscriptionModel(id, name, description, price, currency, repeatType, repeatDay)
    }
}