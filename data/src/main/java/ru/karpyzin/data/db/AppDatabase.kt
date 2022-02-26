package ru.karpyzin.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.karpyzin.data.db.dao.*
import ru.karpyzin.data.db.entity.*

@Database(
    entities = [ReminderEntity::class, HintEntity::class, SubscriptionEntity::class, CounterEntity::class, AccountEntity::class, SettingsEntity::class],
    version = AppDatabase.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_VERSION = 1

        private val rdc = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                db.execSQL("INSERT INTO SettingsEntity (id, isFirstRun) VALUES (null, 1)")
            }
        }

        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "cepka_db")
                .addCallback(rdc)
                .build()
        }
    }

    abstract fun remindersDao(): RemindersDao
    abstract fun hintsDao(): HintDao
    abstract fun subscriptionsDao(): SubscriptionsDao
    abstract fun counterDao(): CounterDao
    abstract fun accountDao(): AccountDao
    abstract fun settingsDao(): SettingsDao
}