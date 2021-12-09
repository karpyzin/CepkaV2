package ru.karpyzin.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.karpyzin.data.db.entity.ReminderEntity
import ru.karpyzin.data.db.entity.SubscriptionEntity

@Dao
interface SubscriptionsDao {
    @Query("SELECT * FROM SubscriptionEntity")
    fun getAllFlow(): Flow<List<SubscriptionEntity>>

    @Query("SELECT * FROM SubscriptionEntity")
    fun getAll(): List<SubscriptionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSubscription(reminder: SubscriptionEntity)

    @Query("DELETE FROM SubscriptionEntity WHERE id = :id")
    fun delete(id: Int)
}