package ru.karpyzin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.karpyzin.data.db.dao.HintDao
import ru.karpyzin.data.db.entity.HintEntity
import ru.karpyzin.data.db.entity.ReminderEntity
import ru.karpyzin.data.db.dao.RemindersDao

@Database(
    entities = [ReminderEntity::class, HintEntity::class],
    version = AppDatabase.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_VERSION = 2
    }

    abstract fun remindersDao(): RemindersDao
    abstract fun hintDao(): HintDao
}