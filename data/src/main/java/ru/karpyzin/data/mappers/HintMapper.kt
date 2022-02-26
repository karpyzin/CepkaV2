package ru.karpyzin.data.mappers

import ru.karpyzin.data.db.entity.HintEntity
import ru.karpyzin.domain.hint.HintModel
import ru.karpyzin.domain.mapper.RevertMapper
import javax.inject.Inject

class HintMapper @Inject constructor(
    private val hintContentMapper: HintContentMapper
) : RevertMapper<HintEntity, HintModel> {
    override fun convert(data: HintEntity): HintModel = with(data) {
        HintModel(id, type, primaryText, secondaryText, contentData?.let { hintContentMapper.convert(it) }, singleReading)
    }

    override fun revert(data: HintModel): HintEntity = with(data) {
        HintEntity(id, type, primaryText, secondaryText, contentData?.let { hintContentMapper.revert(it) }, singleReading)
    }
}