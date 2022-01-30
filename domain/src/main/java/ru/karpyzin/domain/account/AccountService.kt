package ru.karpyzin.domain.account

interface AccountService {
    suspend fun register(email: String, password: String): AccountModel
    suspend fun auth(email: String, password: String): AccountModel
    suspend fun isAuthorized(): Boolean
    suspend fun logout()
}