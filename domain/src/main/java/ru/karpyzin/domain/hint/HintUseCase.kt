package ru.karpyzin.domain.hint

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface HintUseCase {
    val hints: Flow<List<HintModel>>
    suspend fun read(id: Int)
    suspend fun createBaseHints()
}

class HintUseCaseImpl @Inject constructor(private val repository: HintRepository) : HintUseCase {
    override val hints: Flow<List<HintModel>>
        get() = repository.getAll()

    override suspend fun read(id: Int) {
        repository.read(id)
    }

    override suspend fun createBaseHints() {
            val hints = mutableListOf<HintModel>()

            hints.add(HintModel(0, 0, "Привет!", "Давай расскажу вкратце!", null, false))
            hints.add(HintModel(0, 0, "Привет2!", "Давай расскажу вкратце!", null, false))
            hints.add(HintModel(0, 0, "Привет3!", "Давай расскажу вкратце!", null, false))
            hints.add(HintModel(0, 0, "Привет4!", "Давай расскажу вкратце!", null, false))
            repository.addHints(hints)
    }
}