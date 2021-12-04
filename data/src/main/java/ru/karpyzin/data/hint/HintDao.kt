package ru.karpyzin.data.hint

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

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