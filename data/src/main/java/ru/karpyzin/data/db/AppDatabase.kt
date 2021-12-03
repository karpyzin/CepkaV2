package ru.karpyzin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.karpyzin.data.reminders.ReminderEntity
import ru.karpyzin.data.reminders.RemindersDao

@Database(
    entities = [ReminderEntity::class],
    version = AppDatabase.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_VERSION = 1
    }

    abstract fun remindersDao(): RemindersDao
}