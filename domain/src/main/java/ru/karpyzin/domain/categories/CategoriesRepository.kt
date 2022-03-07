package ru.karpyzin.domain.categories

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CategoriesRepository {
    val tasks: Flow<List<CategoryModel>>
    suspend fun getEmoji(id: Int): String?
}