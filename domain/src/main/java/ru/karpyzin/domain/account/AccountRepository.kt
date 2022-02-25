package ru.karpyzin.domain.account

import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    val flow: Flow<AccountModel?>
    suspend fun updateName(name: String)
    suspend fun addAccount(accountModel: AccountModel)
    suspend fun removeAccount()
}