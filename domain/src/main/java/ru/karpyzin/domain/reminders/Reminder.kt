package ru.karpyzin.domain.reminders

data class Reminder(
    val id: Int,
    val title: String,
    val description: String? = null,
    val date: String
)
