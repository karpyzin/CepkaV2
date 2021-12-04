package ru.karpyzin.cepka.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ru.karpyzin.data.hint.HintRepositoryImpl
import ru.karpyzin.domain.hint.HintRepository
import ru.karpyzin.domain.hint.HintUseCase
import ru.karpyzin.domain.hint.HintUseCaseImpl

@Module(includes = [HintsModule.BindsModule::class])
@InstallIn(ApplicationComponent::class)
object HintsModule {

    @Module
    @InstallIn(ApplicationComponent::class)
    abstract class BindsModule {
        @Binds
        abstract fun provideHintUseCase(arg: HintUseCaseImpl): HintUseCase

        @Binds
        abstract fun provideHintRepository(arg: HintRepositoryImpl): HintRepository
    }
}