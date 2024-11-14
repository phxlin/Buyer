package lin.yufan.buylist.ui.features

import lin.yufan.buylist.data.model.ShoppingListItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingListItem)
}