package lin.yufan.buylist.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lin.yufan.buylist.data.model.ShoppingListItem
import lin.yufan.buylist.domain.repository.ShoppingListRepository

class ShoppingListViewModel(
    private val repository: ShoppingListRepository
) : ViewModel() {

    fun insert(item: ShoppingListItem) =
        CoroutineScope(Dispatchers.IO).launch {
            repository.insert(item)
        }

    fun delete(item: ShoppingListItem) =
        CoroutineScope(Dispatchers.IO).launch {
            repository.delete(item)
        }

    fun getAllShoppingItems() = repository.getAllShoppingItems()
}