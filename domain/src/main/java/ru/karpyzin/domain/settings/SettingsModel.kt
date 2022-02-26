package ru.karpyzin.domain.settings

data class SettingsModel(
    val id: Int,
    val isFirstRun: Boolean = false
)