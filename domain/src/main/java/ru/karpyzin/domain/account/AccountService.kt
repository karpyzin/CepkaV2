package ru.karpyzin.domain.account

interface AccountService {
    suspend fun auth(email: String, password: String): AccountModel
}