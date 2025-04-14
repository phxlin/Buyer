package lin.yufan.buyer.data.repository

import lin.yufan.buyer.data.local.BuyListDao
import lin.yufan.buyer.data.model.BuyListItem
import lin.yufan.buyer.domain.repository.BuyListRepository

class BuyListRepositoryImp(
    private val dao: BuyListDao
) : BuyListRepository {

    override suspend fun insert(item: BuyListItem) = dao.insert(item)
    override suspend fun delete(item: BuyListItem) = dao.delete(item)
    override suspend fun update(item: BuyListItem) = dao.update(item)
    override suspend fun getAllBuyListItems(): List<BuyListItem> = dao.getAllBuyListItems()
}