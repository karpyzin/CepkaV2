package ru.karpyzin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.karpyzin.data.db.dao.HintDao
import ru.karpyzin.data.db.dao.RemindersDao
import ru.karpyzin.data.db.dao.SubscriptionsDao
import ru.karpyzin.data.db.entity.HintEntity
import ru.karpyzin.data.db.entity.ReminderEntity
import ru.karpyzin.data.db.entity.SubscriptionEntity

@Database(
    entities = [ReminderEntity::class, HintEntity::class, SubscriptionEntity::class],
    version = AppDatabase.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_VERSION = 3
    }

    abstract fun remindersDao(): RemindersDao
    abstract fun hintsDao(): HintDao
    abstract fun subscriptionsDao(): SubscriptionsDao
}