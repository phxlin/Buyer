package lin.yufan.buyer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import lin.yufan.buyer.data.local.BuyListDatabase
import lin.yufan.buyer.data.model.BuyListItem
import lin.yufan.buyer.data.repository.BuyListRepositoryImp
import lin.yufan.buyer.databinding.ActivityBuyListBinding
import lin.yufan.buyer.ui.adapter.BuyListItemsAdapter
import lin.yufan.buyer.ui.features.AddBuyListItemDialog
import lin.yufan.buyer.ui.features.AddDialogListener
import lin.yufan.buyer.ui.viewmodel.BuyListViewModel

class BuyListActivity : AppCompatActivity() {

    private lateinit var viewModel: BuyListViewModel
    private lateinit var buyListItemsAdapter: BuyListItemsAdapter
    private lateinit var binding: ActivityBuyListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            BuyListViewModelFactory(
                BuyListRepositoryImp(
                    BuyListDatabase(this).getBuyListItemDao()
                )
            )
        )[BuyListViewModel::class.java]

        setRecyclerView()

        buyListItemsAdapter.setOnUpdateItemClickListener {
            viewModel.update(it)
        }
        buyListItemsAdapter.setOnDeleteItemClickListener {
            viewModel.delete(it)
        }

        binding.fab.setOnClickListener {
            AddBuyListItemDialog(this, object : AddDialogListener {
                override fun onAddButtonClicked(item: BuyListItem) {
                    viewModel.insert(item)
                }
            }).show()
        }

        viewModel.getAllBuyListItems()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.buyListItem.observe(this) { itemList ->
            buyListItemsAdapter.diff.submitList(itemList.toList())
        }
    }

    private fun setRecyclerView() {
        buyListItemsAdapter = BuyListItemsAdapter()
        binding.rvShoppingItems.apply {
            adapter = buyListItemsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}