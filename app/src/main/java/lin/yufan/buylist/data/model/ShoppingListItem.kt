package lin.yufan.buylist.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_item")
data class ShoppingListItem(
    @ColumnInfo(name = "item_name")
    var name: String,
    @ColumnInfo(name = "item_unit_price")
    var unitPrice: Double,
    @ColumnInfo(name = "item_total_price")
    var totalPrice: Double,
    @ColumnInfo(name = "item_amount")
    var amount: Int,
    @ColumnInfo(name = "item_is_purchased")
    var isPurchased: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
