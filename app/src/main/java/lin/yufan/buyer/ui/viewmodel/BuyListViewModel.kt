package lin.yufan.buyer.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lin.yufan.buyer.data.model.BuyListItem
import lin.yufan.buyer.domain.repository.BuyListRepository

class BuyListViewModel(
    private val repository: BuyListRepository
) : ViewModel() {

    private val _buyListItems: MutableLiveData<List<BuyListItem>> = MutableLiveData()
    val buyListItem: LiveData<List<BuyListItem>>
        get() = _buyListItems

    fun insert(item: BuyListItem) =
        viewModelScope.launch {
            repository.insert(item)

            getAllBuyListItems()
        }

    fun delete(item: BuyListItem) =
        viewModelScope.launch {
            repository.delete(item)

            getAllBuyListItems()
        }

    fun update(item: BuyListItem) =
        viewModelScope.launch {
            repository.update(item)

            getAllBuyListItems()
        }

    fun getAllBuyListItems() =
        viewModelScope.launch {
            _buyListItems.postValue(repository.getAllBuyListItems())
        }
}