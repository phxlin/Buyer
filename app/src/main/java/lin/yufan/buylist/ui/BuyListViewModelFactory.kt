package lin.yufan.buylist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lin.yufan.buylist.domain.repository.ShoppingListRepository
import lin.yufan.buylist.ui.viewmodel.ShoppingListViewModel

@Suppress("UNCHECKED_CAST")
class BuyListViewModelFactory(
    private val repository: ShoppingListRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingListViewModel(repository) as T
    }
}