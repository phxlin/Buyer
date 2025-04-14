package lin.yufan.buyer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lin.yufan.buyer.data.repository.BuyListRepositoryImp
import lin.yufan.buyer.ui.viewmodel.BuyListViewModel

@Suppress("UNCHECKED_CAST")
class BuyListViewModelFactory(
    private val repository: BuyListRepositoryImp
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BuyListViewModel(repository) as T
    }
}