package ru.karpyzin.data.reminders

import ru.karpyzin.data.base.EntityTransformer
import ru.karpyzin.domain.reminders.Reminder
import javax.inject.Inject

class ReminderEntityTransformer @Inject constructor() : EntityTransformer<ReminderEntity, Reminder> {
    override fun toEntity(data: Reminder): ReminderEntity = with(data) {
        ReminderEntity(id, title, description, date)
    }

    override fun fromEntity(data: ReminderEntity): Reminder = with(data) {
        Reminder(id, title, description, date)
    }
}