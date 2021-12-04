package ru.karpyzin.data.hint

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HintEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo
    val primaryText: String?,
    @ColumnInfo
    val secondaryText: String? = null
)