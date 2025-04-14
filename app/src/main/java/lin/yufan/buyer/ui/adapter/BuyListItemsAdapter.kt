package lin.yufan.buyer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import lin.yufan.buyer.R
import lin.yufan.buyer.data.model.BuyListItem
import lin.yufan.buyer.databinding.BuyListItemBinding

class BuyListItemsAdapter : RecyclerView.Adapter<BuyListItemsAdapter.BuyListItemViewHolder>() {

    val diff = AsyncListDiffer(this, object : DiffUtil.ItemCallback<BuyListItem>() {
        override fun areItemsTheSame(oldItem: BuyListItem, newItem: BuyListItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: BuyListItem, newItem: BuyListItem) =
            oldItem == newItem
    })

    private var onUpdateItemClickListener: ((BuyListItem) -> Unit)? = null
    private var onDeleteItemClickListener: ((BuyListItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BuyListItemViewHolder(
            BuyListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BuyListItemViewHolder, position: Int) =
        holder.bind(diff.currentList[position])

    override fun getItemCount() = diff.currentList.size

    fun setOnUpdateItemClickListener(listener: (BuyListItem) -> Unit) {
        onUpdateItemClickListener = listener
    }

    fun setOnDeleteItemClickListener(listener: (BuyListItem) -> Unit) {
        onDeleteItemClickListener = listener
    }

    inner class BuyListItemViewHolder(private val binding: BuyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BuyListItem) = with(binding) {
            cbPurchased.isChecked = item.isPurchased
            tvName.text = item.name
            tvTotalPrice.text =
                itemView.context.getString(
                    R.string.total_price_format,
                    item.totalPrice
                )
            tvAmount.text = "${item.amount}"

            cbPurchased.setOnClickListener {
                onUpdateItemClickListener?.let {
                    it(
                        item.copy(
                            isPurchased = !item.isPurchased
                        )
                    )
                }
            }
            ivDelete.setOnClickListener {
                onDeleteItemClickListener?.let {
                    it(item)
                }
            }
            ivPlus.setOnClickListener {
                val newAmount = item.amount + 1

                onUpdateItemClickListener?.let {
                    it(
                        item.copy(
                            amount = newAmount,
                            totalPrice = item.unitPrice * newAmount
                        )
                    )
                }
            }
            ivMinus.setOnClickListener {
                val newAmount = item.amount - 1

                onUpdateItemClickListener?.let {
                    it(
                        item.copy(
                            amount = newAmount,
                            totalPrice = item.unitPrice * newAmount
                        )
                    )
                }
            }
        }
    }
}