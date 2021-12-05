package ru.karpyzin.data.reminders

import java.io.Serializable

data class ReminderItemData(
    val userId: String,
    val id: Int,
    val title: String,
    val description: String? = null,
    val date: Long
) : Serializable