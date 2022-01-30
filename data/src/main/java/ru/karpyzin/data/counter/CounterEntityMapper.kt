package ru.karpyzin.data.counter

import ru.karpyzin.data.base.EntityMapper
import ru.karpyzin.data.db.entity.CounterEntity
import ru.karpyzin.domain.counter.CounterModel
import javax.inject.Inject

class CounterEntityMapper @Inject constructor() : EntityMapper<CounterEntity, CounterModel> {
    override fun toEntity(data: CounterModel): CounterEntity = with(data) {
        CounterEntity(id, primaryText, count, maxValue, iconType, date)
    }

    override fun fromEntity(data: CounterEntity): CounterModel = with(data) {
        CounterModel(id, primaryText, count, maxValue, iconType, date)
    }
}