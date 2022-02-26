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
    suspend fun getAll(): List<HintEntity>

    @Query("SELECT * FROM HintEntity WHERE id = :id")
    suspend fun get(id: Int): HintEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHint(hint: HintEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHints(hints: List<HintEntity>)

    @Query("DELETE FROM HintEntity WHERE id = :id")
    suspend fun delete(id: Int)
}