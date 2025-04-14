package lin.yufan.buyer.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buy_list_items")
data class BuyListItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "item_name")
    val name: String,
    @ColumnInfo(name = "item_unit_price")
    val unitPrice: Double,
    @ColumnInfo(name = "item_total_price")
    val totalPrice: Double,
    @ColumnInfo(name = "item_amount")
    val amount: Int,
    @ColumnInfo(name = "item_is_purchased")
    val isPurchased: Boolean
)
