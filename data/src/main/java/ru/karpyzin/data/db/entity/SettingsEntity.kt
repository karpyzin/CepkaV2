package ru.karpyzin.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SettingsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    val isFirstRun: Boolean = false
)