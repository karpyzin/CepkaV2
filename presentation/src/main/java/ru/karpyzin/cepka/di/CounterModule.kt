package ru.karpyzin.cepka.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ru.karpyzin.data.counter.CountersRepositoryImpl
import ru.karpyzin.domain.counter.CountersRepository
import ru.karpyzin.domain.counter.CountersUseCase
import ru.karpyzin.domain.counter.CountersUseCaseImpl

@Module(includes = [CounterModule.BindsModule::class])
@InstallIn(ApplicationComponent::class)
object CounterModule {

    @Module
    @InstallIn(ApplicationComponent::class)
    abstract class BindsModule {
        @Binds
        abstract fun provideCountersUseCase(arg: CountersUseCaseImpl): CountersUseCase

        @Binds
        abstract fun provideCountersRepository(arg: CountersRepositoryImpl): CountersRepository
    }
}