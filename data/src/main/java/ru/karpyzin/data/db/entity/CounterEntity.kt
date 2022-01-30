package ru.karpyzin.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CounterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo
    val primaryText: String,
    @ColumnInfo
    val count: Int = 0,
    @ColumnInfo
    val maxValue: Int,
    @ColumnInfo
    val iconType: Int,
    @ColumnInfo
    val date: Long
)