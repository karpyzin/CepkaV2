package ru.karpyzin.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.karpyzin.domain.hint.HintModel

@Entity
data class HintEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val type: Int,
    @ColumnInfo
    val primaryText: String?,
    @ColumnInfo
    val secondaryText: String? = null,
    @Embedded(prefix = "content_")
    val contentData: DBHintContent?,
    @ColumnInfo
    val singleReading: Boolean = false
) {

    data class DBHintContent(
        val uri: String?,
        val primaryImageUri: String?,
        val secondaryImageUri: String?,
        val text: String?
    )
}