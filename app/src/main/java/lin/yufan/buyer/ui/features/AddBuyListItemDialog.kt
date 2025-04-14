package lin.yufan.buyer.ui.features

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import lin.yufan.buyer.data.model.BuyListItem
import lin.yufan.buyer.databinding.DialogAddBuyListItemBinding

class AddBuyListItemDialog(
    context: Context,
    private var addDialogListener: AddDialogListener
) : AppCompatDialog(context) {

    private lateinit var binding: DialogAddBuyListItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddBuyListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val totalPrice = binding.etTotalPrice.text.toString()
            val amount = binding.etAmount.text.toString()

            if (name.isEmpty() || totalPrice.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Missing information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = BuyListItem(
                name = name,
                unitPrice = totalPrice.toDouble() / amount.toInt(),
                totalPrice = totalPrice.toDouble(),
                amount = amount.toInt(),
                isPurchased = false
            )
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        binding.tvCancel.setOnClickListener {
            cancel()
        }
    }
}