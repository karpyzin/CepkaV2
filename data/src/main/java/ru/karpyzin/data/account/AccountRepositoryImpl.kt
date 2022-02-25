package ru.karpyzin.data.account

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.karpyzin.data.db.AppDatabase
import ru.karpyzin.data.mappers.AccountEntityMapper
import ru.karpyzin.domain.account.AccountModel
import ru.karpyzin.domain.account.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    appDatabase: AppDatabase,
    private val accountEntityMapper: AccountEntityMapper
) : AccountRepository {

    private val dao = appDatabase.accountDao()

    override val flow: Flow<AccountModel?>
        get() = dao.getFlow().map { it?.let { accountEntity -> accountEntityMapper.convert(accountEntity) } }

    override suspend fun updateName(name: String) {
        dao.updateName(name)
    }

    override suspend fun addAccount(accountModel: AccountModel) {
        dao.addAccount(accountEntityMapper.revert(accountModel))
    }

    override suspend fun removeAccount() {
        dao.delete()
    }
}