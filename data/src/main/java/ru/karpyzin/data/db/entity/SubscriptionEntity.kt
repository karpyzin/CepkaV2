package ru.karpyzin.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubscriptionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val description: String?,
    @ColumnInfo
    val price: Float,
    @ColumnInfo
    val currency: String,
    @ColumnInfo
    val repeatType: Int,
    @ColumnInfo
    val repeatDay: Int
)