package ru.karpyzin.data.categories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.karpyzin.data.db.AppDatabase
import ru.karpyzin.data.mappers.CategoryMapper
import ru.karpyzin.domain.categories.CategoriesRepository
import ru.karpyzin.domain.categories.CategoryModel
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    appDatabase: AppDatabase,
    private val categoryMapper: CategoryMapper
) : CategoriesRepository {
    private val dao = appDatabase.categoriesDao()

    override val tasks: Flow<List<CategoryModel>>
        get() = dao.getAllFlow().map { list ->
            categoryMapper.convertList(list)
        }

    override suspend fun getEmoji(id: Int): String? = dao.get(id)
}