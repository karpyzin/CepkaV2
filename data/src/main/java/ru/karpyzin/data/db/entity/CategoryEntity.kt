package ru.karpyzin.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo
    val type: String,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val icon: String
)