package lin.yufan.buyer.ui.features

import lin.yufan.buyer.data.model.BuyListItem

interface AddDialogListener {
    fun onAddButtonClicked(item: BuyListItem)
}