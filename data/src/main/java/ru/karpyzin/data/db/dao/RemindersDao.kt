package ru.karpyzin.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.karpyzin.data.db.entity.ReminderEntity

@Dao
interface RemindersDao {
    @Query("SELECT * FROM ReminderEntity ORDER BY date")
    fun getAllFlow(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM ReminderEntity")
    suspend fun getAll(): List<ReminderEntity>

    @Query("SELECT * FROM ReminderEntity WHERE id = :id")
    suspend fun get(id: Int): ReminderEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReminder(reminder: ReminderEntity)

    @Query("UPDATE ReminderEntity SET date = :newDate WHERE id = :id")
    suspend fun changeDate(id: Int, newDate: Long)

    @Query("DELETE FROM ReminderEntity WHERE id = :id")
    suspend fun delete(id: Int)
}