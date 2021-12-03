package ru.karpyzin.data.reminders

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RemindersDao {
    @Query("SELECT * FROM ReminderEntity")
    fun getAllFlow(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM ReminderEntity")
    fun getAll(): List<ReminderEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReminder(reminder: ReminderEntity)

    @Query("DELETE FROM ReminderEntity WHERE id = :id")
    fun delete(id: Int)
}