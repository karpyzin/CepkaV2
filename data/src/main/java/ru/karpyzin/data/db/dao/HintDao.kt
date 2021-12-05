package ru.karpyzin.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.karpyzin.data.db.entity.HintEntity

@Dao
interface HintDao {
    @Query("SELECT * FROM HintEntity")
    fun getAllFlow(): Flow<List<HintEntity>>

    @Query("SELECT * FROM HintEntity")
    fun getAll(): List<HintEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReminder(reminder: HintEntity)

    @Query("DELETE FROM HintEntity WHERE id = :id")
    fun delete(id: Int)
}