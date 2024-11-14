package lin.yufan.buylist.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import lin.yufan.buylist.R
import lin.yufan.buylist.data.model.ShoppingListItem
import lin.yufan.buylist.ui.adapter.ShoppingItemAdapter
import lin.yufan.buylist.ui.features.AddDialogListener
import lin.yufan.buylist.ui.features.AddShoppingItemDialog
import lin.yufan.buylist.ui.viewmodel.ShoppingListViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class BuyListActivity : ComponentActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: BuyListViewModelFactory by instance()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_list)

        val viewModel = ViewModelProvider(this, factory)[ShoppingListViewModel::class.java]
        val shoppingItemAdapter = ShoppingItemAdapter(listOf(), viewModel)

        findViewById<RecyclerView>(R.id.rvShoppingItems).apply {
            adapter = shoppingItemAdapter
            layoutManager = LinearLayoutManager(context)
        }
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingListItem) {
                    viewModel.insert(item)
                }
            }).show()
        }

        viewModel.getAllShoppingItems().observe(this) {
            shoppingItemAdapter.items = it
            shoppingItemAdapter.notifyDataSetChanged()
        }
    }
}