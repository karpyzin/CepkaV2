package ru.karpyzin.domain.account

import javax.inject.Inject

interface AccountUseCase {
    suspend fun auth(email: String, password: String): AccountModel //TODO
}

class AccountUseCaseImpl @Inject constructor(private val accountService: AccountService) : AccountUseCase {
    override suspend fun auth(email: String, password: String): AccountModel {
        return accountService.auth(email, password)
    }

}