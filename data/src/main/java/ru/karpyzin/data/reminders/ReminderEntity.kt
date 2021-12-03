package ru.karpyzin.data.reminders

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val description: String? = null,
    @ColumnInfo
    val date: String,
)