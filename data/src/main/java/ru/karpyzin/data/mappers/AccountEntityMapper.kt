package ru.karpyzin.data.mappers

import ru.karpyzin.data.db.entity.AccountEntity
import ru.karpyzin.domain.account.AccountModel
import ru.karpyzin.domain.mapper.RevertMapper
import javax.inject.Inject

class AccountEntityMapper @Inject constructor() : RevertMapper<AccountEntity, AccountModel> {
    override fun convert(data: AccountEntity): AccountModel = with(data) {
        AccountModel(id, name, profileUrl, notificationsCount)
    }

    override fun revert(data: AccountModel): AccountEntity = with(data) {
        AccountEntity(id, name, profileUrl, notificationsCount)
    }
}