package lin.yufan.buylist.ui.features

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import lin.yufan.buylist.R
import lin.yufan.buylist.data.model.ShoppingListItem

class AddShoppingItemDialog(
    context: Context,
    private var addDialogListener: AddDialogListener
) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        findViewById<TextView>(R.id.tvAdd)?.setOnClickListener {
            val name = findViewById<EditText>(R.id.etName)?.text.toString()
            val totalPrice = findViewById<EditText>(R.id.etTotalPrice)?.text.toString()
            val amount = findViewById<EditText>(R.id.etAmount)?.text.toString()

            if (name.isEmpty() || totalPrice.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Missing information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingListItem(
                name = name,
                unitPrice = totalPrice.toDouble() / amount.toInt(),
                totalPrice = totalPrice.toDouble(),
                amount = amount.toInt(),
                isPurchased = false
            )
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        findViewById<TextView>(R.id.tvCancel)?.setOnClickListener {
            cancel()
        }
    }
}