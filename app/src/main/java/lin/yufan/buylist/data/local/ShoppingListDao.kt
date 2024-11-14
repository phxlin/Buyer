package lin.yufan.buylist.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import lin.yufan.buylist.data.model.ShoppingListItem

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ShoppingListItem)

    @Delete
    suspend fun delete(ite: ShoppingListItem)

    @Query("SELECT * FROM shopping_list_item")
    fun getAllShoppingItems(): LiveData<List<ShoppingListItem>>
}