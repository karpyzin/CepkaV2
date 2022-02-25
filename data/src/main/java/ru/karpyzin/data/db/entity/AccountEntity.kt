package ru.karpyzin.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountEntity(
    @PrimaryKey val id: String,
    @ColumnInfo
    val name: String?,
    @ColumnInfo
    val profileUrl: String?,
    @ColumnInfo
    val notificationsCount: Int
)