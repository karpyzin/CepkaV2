package ru.karpyzin.cepka.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.create
import ru.karpyzin.data.reminders.RemindersRepositoryImpl
import ru.karpyzin.data.reminders.RemindersServiceImpl
import ru.karpyzin.domain.reminders.RemindersRepository
import ru.karpyzin.domain.reminders.RemindersService
import ru.karpyzin.domain.reminders.RemindersUseCase
import ru.karpyzin.domain.reminders.RemindersUseCaseImpl

@Module(includes = [RemindersModule.BindsModule::class])
@InstallIn(ApplicationComponent::class)
object RemindersModule {

    @Provides
    fun eventApi(retrofit: Retrofit): RemindersServiceImpl.RemindersApi = retrofit.create()

    @Module
    @InstallIn(ApplicationComponent::class)
    abstract class BindsModule {
        @Binds
        abstract fun provideRemindersUseCase(arg: RemindersUseCaseImpl): RemindersUseCase

        @Binds
        abstract fun provideRemindersService(arg: RemindersServiceImpl): RemindersService

        @Binds
        abstract fun provideRemindersRepository(arg: RemindersRepositoryImpl): RemindersRepository
    }
}