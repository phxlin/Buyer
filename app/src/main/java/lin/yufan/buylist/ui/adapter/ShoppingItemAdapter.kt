package lin.yufan.buylist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lin.yufan.buylist.R
import lin.yufan.buylist.data.model.ShoppingListItem
import lin.yufan.buylist.ui.viewmodel.ShoppingListViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingListItem>,
    private val viewModel: ShoppingListViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ShoppingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.shopping_item,
                parent,
                false
            )
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val currentItem = items[position]

        with(holder.itemView) {
            val isPurchasedCheckBox = findViewById<CheckBox>(R.id.cbPurchased)
            val itemNameTextView = findViewById<TextView>(R.id.tvName)
            val itemTotalPriceTextView = findViewById<TextView>(R.id.tvTotalPrice)
            val deleteButtonImageView = findViewById<ImageView>(R.id.ivDelete)
            val plusButtonImageView = findViewById<ImageView>(R.id.ivPlus)
            val itemAmountTextView = findViewById<TextView>(R.id.tvAmount)
            val minusButtonImageView = findViewById<ImageView>(R.id.ivMinus)

            isPurchasedCheckBox.isChecked = currentItem.isPurchased
            itemNameTextView.text = currentItem.name
            itemTotalPriceTextView.text =
                context.getString(
                    R.string.total_price_format,
                    currentItem.totalPrice
                )
            itemAmountTextView.text = "${currentItem.amount}"

            isPurchasedCheckBox.setOnClickListener {
                currentItem.isPurchased = !currentItem.isPurchased
                viewModel.insert(currentItem)
            }
            deleteButtonImageView.setOnClickListener {
                viewModel.delete(currentItem)
            }
            plusButtonImageView.setOnClickListener {
                currentItem.amount++
                currentItem.totalPrice = currentItem.unitPrice * currentItem.amount
                viewModel.insert(currentItem)
            }
            minusButtonImageView.setOnClickListener {
                if (currentItem.amount > 0) {
                    currentItem.amount--
                    currentItem.totalPrice = currentItem.unitPrice * currentItem.amount
                }
                viewModel.insert(currentItem)
            }
        }
    }

    inner class ShoppingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}