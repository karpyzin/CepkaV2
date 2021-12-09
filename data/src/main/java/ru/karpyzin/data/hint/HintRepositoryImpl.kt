package ru.karpyzin.data.hint

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.karpyzin.data.db.AppDatabase
import ru.karpyzin.data.mappers.HintMapper
import ru.karpyzin.domain.hint.HintModel
import ru.karpyzin.domain.hint.HintRepository
import javax.inject.Inject

class HintRepositoryImpl @Inject constructor(appDatabase: AppDatabase, private val hintMapper: HintMapper) : HintRepository {

    private val dao = appDatabase.hintsDao()

    override fun getAll(): Flow<List<HintModel>> {

        /*list.add(HintModel(0, "Привет!", "Как прошел твой день?"))
        list.add(HintModel(0, "Чао!", "Как прошел твой день?"))
        list.add(HintModel(0, "Что?!", "Как прошел твой день?"))
        list.add(HintModel(0, "Что ты мне сделаешь?!", "Как прошел твой день?"))*/

        return dao.getAllFlow().map { hintMapper.convertList(it) }
    }
}