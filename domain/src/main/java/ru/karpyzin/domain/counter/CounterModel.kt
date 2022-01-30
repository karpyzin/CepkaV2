package ru.karpyzin.domain.counter

data class CounterModel(
    val id: Int,
    val primaryText: String,
    val count: Int = 0,
    val maxValue: Int,
    val iconType: Int,
    val date: Long
) {
    companion object {
        const val TYPE_WATER = 0
    }
}