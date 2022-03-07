package ru.karpyzin.data.reminders

import ru.karpyzin.domain.mapper.Mapper
import ru.karpyzin.domain.reminders.ReminderModel
import javax.inject.Inject

class ReminderItemMapper @Inject constructor() : Mapper<ReminderItemData, ReminderModel> {
    override fun convert(data: ReminderItemData): ReminderModel = with(data) {
        ReminderModel(id, title, description, date, isArchived, emoji)
    }
}