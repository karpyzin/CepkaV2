package ru.karpyzin.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.karpyzin.data.db.entity.CategoryEntity

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM CategoryEntity")
    fun getAllFlow(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM CategoryEntity")
    suspend fun getAll(): List<CategoryEntity>

    @Query("SELECT icon FROM CategoryEntity WHERE id = :id")
    suspend fun get(id: Int): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(category: CategoryEntity)

    @Query("DELETE FROM CategoryEntity WHERE id = :id")
    suspend fun delete(id: Int)
}