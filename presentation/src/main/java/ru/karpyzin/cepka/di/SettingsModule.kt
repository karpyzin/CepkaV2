package ru.karpyzin.cepka.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ru.karpyzin.data.settings.SettingsRepositoryImpl
import ru.karpyzin.domain.settings.SettingsRepository
import ru.karpyzin.domain.settings.SettingsUseCase
import ru.karpyzin.domain.settings.SettingsUseCaseImpl

@Module(includes = [SettingsModule.BindsModule::class])
@InstallIn(ApplicationComponent::class)
object SettingsModule {

    @Module
    @InstallIn(ApplicationComponent::class)
    abstract class BindsModule {
        @Binds
        abstract fun provideSettingsUseCase(arg: SettingsUseCaseImpl): SettingsUseCase

        @Binds
        abstract fun provideSettingsRepository(arg: SettingsRepositoryImpl): SettingsRepository
    }
}