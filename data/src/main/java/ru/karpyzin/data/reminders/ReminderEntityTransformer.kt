package ru.karpyzin.data.reminders

import ru.karpyzin.data.base.EntityTransformer
import ru.karpyzin.data.db.entity.ReminderEntity
import ru.karpyzin.domain.reminders.ReminderModel
import javax.inject.Inject

class ReminderEntityTransformer @Inject constructor() : EntityTransformer<ReminderEntity, ReminderModel> {
    override fun toEntity(data: ReminderModel): ReminderEntity = with(data) {
        ReminderEntity(id, title, description, date)
    }

    override fun fromEntity(data: ReminderEntity): ReminderModel = with(data) {
        ReminderModel(id, title, description, date)
    }
}