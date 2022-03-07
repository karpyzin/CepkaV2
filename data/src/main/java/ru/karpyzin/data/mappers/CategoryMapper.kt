package ru.karpyzin.data.mappers

import ru.karpyzin.data.db.entity.CategoryEntity
import ru.karpyzin.domain.categories.CategoryModel
import ru.karpyzin.domain.mapper.Mapper
import javax.inject.Inject

class CategoryMapper @Inject constructor() : Mapper<CategoryEntity, CategoryModel> {
    override fun convert(data: CategoryEntity): CategoryModel = with(data) {
        CategoryModel(id, type, name, icon)
    }
}