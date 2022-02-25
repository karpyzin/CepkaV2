package ru.karpyzin.domain.account

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AccountUseCase {
    val flow: Flow<AccountModel?>
    suspend fun auth(email: String, password: String): AccountModel
    suspend fun register(email: String, password: String): AccountModel
    suspend fun updateName(name: String)
    suspend fun isAuthorized(): Boolean
    suspend fun logout()
}

class AccountUseCaseImpl @Inject constructor(
    private val accountService: AccountService,
    private val accountRepository: AccountRepository
) : AccountUseCase {

    override val flow: Flow<AccountModel?> get() = accountRepository.flow

    override suspend fun auth(email: String, password: String): AccountModel {
        val result = accountService.auth(email, password)

        accountRepository.addAccount(result)

        return result
    }

    override suspend fun register(email: String, password: String): AccountModel {
        val result = accountService.register(email, password)

        accountRepository.addAccount(result)

        return result
    }

    override suspend fun updateName(name: String) {
        val success = accountService.updateName(name)

        if (success) {
            accountRepository.updateName(name)
        }
    }

    override suspend fun isAuthorized(): Boolean {
        return accountService.isAuthorized()
    }

    override suspend fun logout() {
        accountService.logout()
        accountRepository.removeAccount()
    }

}