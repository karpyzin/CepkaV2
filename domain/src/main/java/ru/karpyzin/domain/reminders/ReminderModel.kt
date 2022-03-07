package ru.karpyzin.domain.reminders

import java.io.Serializable

data class ReminderModel(
    val id: Int,
    val title: String,
    val description: String? = null,
    val date: Long,
    val isArchived: Boolean,
    val emoji: String?
): Serializable
