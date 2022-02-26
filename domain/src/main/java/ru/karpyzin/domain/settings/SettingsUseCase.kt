package ru.karpyzin.domain.settings

import javax.inject.Inject

interface SettingsUseCase {
    suspend fun get(): SettingsModel?
    suspend fun firstTimeRan()
}

class SettingsUseCaseImpl @Inject constructor(
    private val settingsRepository: SettingsRepository
) : SettingsUseCase {
    override suspend fun get(): SettingsModel? {
        return settingsRepository.getAllSettings()
    }

    override suspend fun firstTimeRan() {
        settingsRepository.setFirstInitialized(false)
    }

}