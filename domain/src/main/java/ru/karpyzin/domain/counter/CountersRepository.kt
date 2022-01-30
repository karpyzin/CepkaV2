package ru.karpyzin.domain.counter

import kotlinx.coroutines.flow.Flow

interface CountersRepository {
    fun getAllFlow(): Flow<List<CounterModel>>
    suspend fun increaseCount(id: Int)
    suspend fun decreaseCount(id: Int)
}