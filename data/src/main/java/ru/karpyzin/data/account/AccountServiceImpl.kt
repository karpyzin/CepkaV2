package ru.karpyzin.data.account

import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import ru.karpyzin.domain.account.AccountModel
import ru.karpyzin.domain.account.AccountService
import timber.log.Timber
import javax.inject.Inject

class AccountServiceImpl @Inject constructor() : AccountService {
    override suspend fun auth(email: String, password: String): AccountModel {
        val fa = FirebaseAuth.getInstance()
        val user = fa.currentUser

        return if (user == null) {
            val new = Tasks.await(fa.signInWithEmailAndPassword(email, password)).user
            AccountModel(new?.uid ?: "", new?.displayName, new?.photoUrl?.toString())
        } else {
            AccountModel(user.uid ?: "", user.displayName, user.photoUrl?.toString())
        }
    }
}