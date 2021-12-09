package ru.karpyzin.cepka.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ru.karpyzin.data.subscriptions.SubscriptionsRepositoryImpl
import ru.karpyzin.domain.subscriptions.SubscriptionsRepository
import ru.karpyzin.domain.subscriptions.SubscriptionsUseCase
import ru.karpyzin.domain.subscriptions.SubscriptionsUseCaseImpl

@Module(includes = [SubscriptionsModule.BindsModule::class])
@InstallIn(ApplicationComponent::class)
object SubscriptionsModule {

    /*@Provides
    fun eventApi(retrofit: Retrofit): SubscriptionsServiceImpl.SubscriptionsApi = retrofit.create()*/

    @Module
    @InstallIn(ApplicationComponent::class)
    abstract class BindsModule {
        @Binds
        abstract fun provideSubscriptionsUseCase(arg: SubscriptionsUseCaseImpl): SubscriptionsUseCase

        /*@Binds
        abstract fun provideSubscriptionsService(arg: SubscriptionsServiceImpl): SubscriptionsService*/

        @Binds
        abstract fun provideSubscriptionsRepository(arg: SubscriptionsRepositoryImpl): SubscriptionsRepository
    }
}