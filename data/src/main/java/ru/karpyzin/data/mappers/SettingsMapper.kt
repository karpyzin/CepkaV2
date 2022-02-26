package ru.karpyzin.data.mappers

import ru.karpyzin.data.db.entity.SettingsEntity
import ru.karpyzin.domain.mapper.Mapper
import ru.karpyzin.domain.settings.SettingsModel
import javax.inject.Inject

class SettingsMapper @Inject constructor() : Mapper<SettingsEntity, SettingsModel> {
    override fun convert(data: SettingsEntity): SettingsModel = with(data) {
        SettingsModel(id, isFirstRun)
    }
}