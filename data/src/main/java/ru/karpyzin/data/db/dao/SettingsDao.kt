package ru.karpyzin.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.karpyzin.data.db.entity.SettingsEntity

@Dao
interface SettingsDao {

    @Query("SELECT * FROM SettingsEntity LIMIT 1")
    fun getFlow(): Flow<SettingsEntity?>

    @Query("SELECT * FROM SettingsEntity LIMIT 1")
    suspend fun get(): SettingsEntity?

    @Query("DELETE FROM SettingsEntity")
    suspend fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAccount(settings: SettingsEntity)

    @Query("UPDATE SettingsEntity SET isFirstRun = :isFirstRun")
    suspend fun updateFirstRun(isFirstRun: Boolean)
}