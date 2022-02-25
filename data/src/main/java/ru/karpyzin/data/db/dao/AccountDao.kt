package ru.karpyzin.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.karpyzin.data.db.entity.AccountEntity

@Dao
interface AccountDao {

    @Query("SELECT * FROM AccountEntity LIMIT 1")
    fun getFlow(): Flow<AccountEntity?>

    @Query("DELETE FROM AccountEntity")
    suspend fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAccount(accountEntity: AccountEntity)

    @Query("UPDATE AccountEntity SET name = :name")
    suspend fun updateName(name: String)
}