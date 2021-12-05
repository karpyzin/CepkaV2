package ru.karpyzin.data.firestore

import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.karpyzin.domain.reminders.ReminderModel
import timber.log.Timber
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FireStore @Inject constructor(private val gson: Gson, private val firestore: FirebaseFirestore) {

    data class Data(val data: Any)

    companion object {
        const val REMINDERS = "reminders"
    }

    suspend fun <R> getData(table: String, userId: String, classType: Type): R = withContext(Dispatchers.IO) {
        val collection = firestore.collection(table)
        Timber.e("USERID $userId")
        val list = collection.document(userId)
        val result = Tasks.await(list.get()).data?.get("data")
        val gg = gson.toJson(result)
        val resultEnd = gson.fromJson<List<ReminderModel>>(gg, classType) as? R
        return@withContext resultEnd ?: throw Exception("")
    }

    suspend fun uploadData(table: String, userId: String, data: List<Any>): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            val collection = firestore.collection(table)
            val list = collection.document(userId)
            list.set(Data(data))
            true
        } catch (e: Throwable) {
            false
        }
    }
}