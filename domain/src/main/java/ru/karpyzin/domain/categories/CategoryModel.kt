package ru.karpyzin.domain.categories

data class CategoryModel(
    val id: Int,
    val type: String,
    val name: String,
    val icon: String
) {
    companion object {
        const val TYPE_TASK = 0
        const val TYPE_FINANCE = 1
    }
}