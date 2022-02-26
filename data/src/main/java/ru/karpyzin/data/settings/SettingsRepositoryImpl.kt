package ru.karpyzin.data.settings

import ru.karpyzin.data.db.AppDatabase
import ru.karpyzin.data.mappers.SettingsMapper
import ru.karpyzin.domain.settings.SettingsModel
import ru.karpyzin.domain.settings.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    appDatabase: AppDatabase,
    private val settingsMapper: SettingsMapper
) : SettingsRepository {
    private val dao = appDatabase.settingsDao()

    override suspend fun getAllSettings(): SettingsModel? {
        return dao.get()?.let { settingsMapper.convert(it) }
    }

    override suspend fun setFirstInitialized(value: Boolean) {
        dao.updateFirstRun(value)
    }
}