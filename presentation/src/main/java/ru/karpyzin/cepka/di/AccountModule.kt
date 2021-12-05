package ru.karpyzin.cepka.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ru.karpyzin.data.account.AccountServiceImpl
import ru.karpyzin.domain.account.AccountService
import ru.karpyzin.domain.account.AccountUseCase
import ru.karpyzin.domain.account.AccountUseCaseImpl

@Module(includes = [AccountModule.BindsModule::class])
@InstallIn(ApplicationComponent::class)
object AccountModule {

    @Module
    @InstallIn(ApplicationComponent::class)
    abstract class BindsModule {
        @Binds
        abstract fun provideAccountUseCase(arg: AccountUseCaseImpl): AccountUseCase

        @Binds
        abstract fun provideAccountService(arg: AccountServiceImpl): AccountService
    }
}