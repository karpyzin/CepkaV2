package ru.karpyzin.domain.account

import javax.inject.Inject

interface AccountUseCase {
    suspend fun auth(email: String, password: String): AccountModel
    suspend fun register(email: String, password: String): AccountModel
    suspend fun isAuthorized(): Boolean
    suspend fun logout()
}

class AccountUseCaseImpl @Inject constructor(private val accountService: AccountService) : AccountUseCase {

    override suspend fun auth(email: String, password: String): AccountModel {
        return accountService.auth(email, password)
    }

    override suspend fun register(email: String, password: String): AccountModel {
        return accountService.register(email, password)
    }

    override suspend fun isAuthorized(): Boolean {
        return accountService.isAuthorized()
    }

    override suspend fun logout() {
        accountService.logout()
    }

}