package ru.karpyzin.data.counter

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.karpyzin.data.db.AppDatabase
import ru.karpyzin.domain.counter.CounterModel
import ru.karpyzin.domain.counter.CountersRepository
import javax.inject.Inject

class CountersRepositoryImpl @Inject constructor(
    appDatabase: AppDatabase,
    private val counterEntityMapper: CounterEntityMapper
) : CountersRepository {
    private val dao = appDatabase.counterDao()

    override fun getAllFlow(): Flow<List<CounterModel>> {
        return dao.getAllFlow().map { it.map { item -> counterEntityMapper.fromEntity(item) } }
    }

    override suspend fun increaseCount(id: Int) {
        dao.increaseCount(id)
    }

    override suspend fun decreaseCount(id: Int) {
        dao.decreaseCount(id)
    }
}