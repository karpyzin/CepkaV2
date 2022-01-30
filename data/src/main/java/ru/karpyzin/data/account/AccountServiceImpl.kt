package ru.karpyzin.data.account

import com.google.android.gms.tasks.Tasks
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import ru.karpyzin.data.error.CepkaException.Companion.ERROR_WHILE_REQUEST
import ru.karpyzin.domain.account.AccountModel
import ru.karpyzin.domain.account.AccountService
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class AccountServiceImpl @Inject constructor() : AccountService {

    override suspend fun register(email: String, password: String): AccountModel {
        val fa = FirebaseAuth.getInstance()
        val accountData = Tasks.await(fa.createUserWithEmailAndPassword(email, password)).user

        if (accountData != null) {
            return AccountModel(accountData.uid, accountData.displayName, accountData.photoUrl?.toString())
        } else {
            throw Exception(ERROR_WHILE_REQUEST)
        }
    }

    override suspend fun auth(email: String, password: String): AccountModel {
        val fa = FirebaseAuth.getInstance()
        val user = Tasks.await(fa.signInWithEmailAndPassword(email, password)).user

        return if (user != null) {
            AccountModel(user.uid, user.displayName, user.photoUrl?.toString())
        } else {
            throw Exception(ERROR_WHILE_REQUEST)
        }
    }

    override suspend fun isAuthorized(): Boolean {
        return runCatching { FirebaseAuth.getInstance().currentUser != null }.getOrDefault(false)
    }

    override suspend fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}