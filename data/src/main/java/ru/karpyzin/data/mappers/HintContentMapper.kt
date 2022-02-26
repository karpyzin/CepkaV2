package ru.karpyzin.data.mappers

import ru.karpyzin.data.db.entity.HintEntity
import ru.karpyzin.domain.hint.HintModel
import ru.karpyzin.domain.mapper.RevertMapper
import javax.inject.Inject

class HintContentMapper @Inject constructor() : RevertMapper<HintEntity.DBHintContent, HintModel.HintContentModel> {
    override fun convert(data: HintEntity.DBHintContent): HintModel.HintContentModel = with(data) {
        HintModel.HintContentModel(uri, primaryImageUri, secondaryImageUri, text)
    }

    override fun revert(data: HintModel.HintContentModel): HintEntity.DBHintContent = with(data) {
        HintEntity.DBHintContent(uri, primaryImageUri, secondaryImageUri, text)
    }
}