package ru.karpyzin.data.mappers

import ru.karpyzin.data.hint.HintEntity
import ru.karpyzin.domain.hint.HintModel
import ru.karpyzin.domain.mapper.Mapper
import javax.inject.Inject

class HintMapper @Inject constructor() : Mapper<HintEntity, HintModel> {
    override fun convert(data: HintEntity): HintModel = with(data) {
        HintModel(id, primaryText, secondaryText)
    }
}