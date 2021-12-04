package ru.karpyzin.domain.hint

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface HintUseCase {
    val hints: Flow<List<HintModel>>
}

class HintUseCaseImpl @Inject constructor(private val repository: HintRepository) : HintUseCase {
    override val hints: Flow<List<HintModel>>
        get() = repository.getAll()
}