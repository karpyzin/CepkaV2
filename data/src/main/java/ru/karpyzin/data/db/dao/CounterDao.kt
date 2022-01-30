package ru.karpyzin.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.karpyzin.data.db.entity.CounterEntity

@Dao
interface CounterDao {

    @Query("UPDATE CounterEntity SET count = count + 1 WHERE id = :id")
    suspend fun increaseCount(id: Int)

    @Query("UPDATE CounterEntity SET count = count - 1 WHERE id = :id")
    suspend fun decreaseCount(id: Int)

    @Query("SELECT * FROM CounterEntity")
    fun getAllFlow(): Flow<List<CounterEntity>>

    @Query("SELECT * FROM CounterEntity")
    suspend fun getAll(): List<CounterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCounter(reminder: CounterEntity)

    @Query("DELETE FROM CounterEntity WHERE id = :id")
    suspend fun delete(id: Int)
}