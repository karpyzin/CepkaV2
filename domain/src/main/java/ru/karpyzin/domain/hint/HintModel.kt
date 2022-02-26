package ru.karpyzin.domain.hint

data class HintModel(
    val id: Int,
    val type: Int,
    val primaryText: String?,
    val secondaryText: String?,
    val contentData: HintContentModel?,
    val singleReading: Boolean
) {
    companion object {
        const val TYPE_LOCAL = 0
        const val TYPE_REMOTE_WEATHER = 1
        const val TYPE_REMOTE_STORIES = 2
    }

    data class HintContentModel(
        val uri: String?,
        val primaryImageUri: String?,
        val secondaryImageUri: String?,
        val text: String?
    )
}