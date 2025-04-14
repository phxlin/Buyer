package lin.yufan.buyer.domain.repository

import lin.yufan.buyer.data.model.BuyListItem

interface BuyListRepository {

    suspend fun insert(item: BuyListItem)

    suspend fun delete(item: BuyListItem)

    suspend fun update(item: BuyListItem)

    suspend fun getAllBuyListItems(): List<BuyListItem>
}