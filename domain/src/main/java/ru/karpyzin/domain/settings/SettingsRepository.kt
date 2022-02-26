package ru.karpyzin.domain.settings

interface SettingsRepository {
    suspend fun getAllSettings(): SettingsModel?
    suspend fun setFirstInitialized(value: Boolean)
}