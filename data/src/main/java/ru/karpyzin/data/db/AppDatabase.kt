package ru.karpyzin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.karpyzin.data.db.dao.*
import ru.karpyzin.data.db.entity.*

@Database(
    entities = [ReminderEntity::class, HintEntity::class, SubscriptionEntity::class, CounterEntity::class, AccountEntity::class],
    version = AppDatabase.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_VERSION = 4
    }

    abstract fun remindersDao(): RemindersDao
    abstract fun hintsDao(): HintDao
    abstract fun subscriptionsDao(): SubscriptionsDao
    abstract fun counterDao(): CounterDao
    abstract fun accountDao(): AccountDao
}