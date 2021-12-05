package ru.karpyzin.data.reminders

import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.karpyzin.data.db.AppDatabase
import ru.karpyzin.domain.reminders.ReminderModel
import ru.karpyzin.domain.reminders.RemindersRepository
import timber.log.Timber
import javax.inject.Inject

class RemindersRepositoryImpl @Inject constructor(
    private val gson: Gson,
    appDatabase: AppDatabase,
    private val reminderEntityTransformer: ReminderEntityTransformer
) : RemindersRepository {

    private val remindersDao = appDatabase.remindersDao()

    override val reminderModels: List<ReminderModel>
        get() = remindersDao.getAll().map { reminderEntityTransformer.fromEntity(it) }
    override val remindersFlow: Flow<List<ReminderModel>>
        get() = remindersDao.getAllFlow().map { list -> list.map { reminderEntityTransformer.fromEntity(it) } }

    override suspend fun add(title: String, description: String?, date: Long) {
        remindersDao.addReminder(ReminderEntity(0, title, description, date))
    }

    override suspend fun complete(id: Int) {
        remindersDao.delete(id)
    }

    override suspend fun getTestData() {
        val db = FirebaseFirestore.getInstance().collection("reminders")
        val new = db.document("4cr3c3443")
        new.set(mapOf("dick" to 23))

        val item1 = Tasks.await(db.document("table").get())
        val item2 = Tasks.await(db.document("4Z9MNmrz3QllOrtm07fX").get())
        Timber.e("RESULT 1 ${item1.data} 2 ${item2.data}")
        val raw = gson.toJson(item1.data)
        val sss = gson.fromJson(raw, ReminderModel::class.java)
        Timber.e("GSON ${sss.title}")
    }
}