package ru.karpyzin.domain.subscriptions

data class SubscriptionModel(
    val id: Int,
    val name: String,
    val description: String?,
    val price: Float,
    val currency: String,
    val repeatType: Int,
    val repeatDay: Int
) {
    private companion object {
        const val MONTH = 0
        const val WEEK = 1
        const val DAY = 2
    }
}
