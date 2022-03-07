package ru.karpyzin.domain.categories

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CategoriesUseCase {
    val tasks: Flow<List<CategoryModel>>
}

class CategoriesUseCaseImpl @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) : CategoriesUseCase {
    override val tasks: Flow<List<CategoryModel>>
        get() = categoriesRepository.tasks
}