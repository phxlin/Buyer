package lin.yufan.buylist.domain.repository

import lin.yufan.buylist.data.local.ShoppingListDatabase
import lin.yufan.buylist.data.model.ShoppingListItem

class ShoppingListRepository(
    private val db: ShoppingListDatabase
) {
    suspend fun insert(item: ShoppingListItem) = db.getShoppingListDao().insert(item)
    suspend fun delete(item: ShoppingListItem) = db.getShoppingListDao().delete(item)
    fun getAllShoppingItems() = db.getShoppingListDao().getAllShoppingItems()
}