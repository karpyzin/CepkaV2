package ru.karpyzin.data.db.entity

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
    val date: Long,
    @ColumnInfo
    val isArchived: Boolean = false,
    @ColumnInfo
    val emoji: String?
)