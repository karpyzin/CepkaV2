package ru.karpyzin.cepka.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ru.karpyzin.data.categories.CategoriesRepositoryImpl
import ru.karpyzin.domain.categories.CategoriesRepository
import ru.karpyzin.domain.categories.CategoriesUseCase
import ru.karpyzin.domain.categories.CategoriesUseCaseImpl

@Module(includes = [CategoriesModule.BindsModule::class])
@InstallIn(ApplicationComponent::class)
object CategoriesModule {

    @Module
    @InstallIn(ApplicationComponent::class)
    abstract class BindsModule {
        @Binds
        abstract fun provideCategoriesUseCase(arg: CategoriesUseCaseImpl): CategoriesUseCase

        @Binds
        abstract fun provideCategoriesRepository(arg: CategoriesRepositoryImpl): CategoriesRepository
    }
}