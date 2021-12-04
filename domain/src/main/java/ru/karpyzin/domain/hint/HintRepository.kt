package ru.karpyzin.domain.hint

import kotlinx.coroutines.flow.Flow

interface HintRepository {
    fun getAll(): Flow<List<HintModel>>
}