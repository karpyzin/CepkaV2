package ru.karpyzin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.karpyzin.data.hint.HintDao
import ru.karpyzin.data.hint.HintEntity
import ru.karpyzin.data.reminders.ReminderEntity
import ru.karpyzin.data.reminders.RemindersDao

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