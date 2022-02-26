package ru.karpyzin.domain.hint

import kotlinx.coroutines.flow.Flow

interface HintRepository {
    fun getAll(): Flow<List<HintModel>>
    suspend fun get(id: Int): HintModel?
    suspend fun read(id: Int)
    suspend fun addHints(hints: List<HintModel>)
}