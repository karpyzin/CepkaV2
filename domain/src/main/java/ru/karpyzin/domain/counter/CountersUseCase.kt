package ru.karpyzin.domain.counter

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CountersUseCase {
    val counters: Flow<List<CounterModel>>
    suspend fun increaseCount(id: Int)
    suspend fun decreaseCount(id: Int)
}

class CountersUseCaseImpl @Inject constructor(
    private val countersRepository: CountersRepository
) : CountersUseCase {

    override val counters: Flow<List<CounterModel>>
        get() = countersRepository.getAllFlow()

    override suspend fun increaseCount(id: Int) {
        countersRepository.increaseCount(id)
    }

    override suspend fun decreaseCount(id: Int) {
        countersRepository.decreaseCount(id)
    }

}