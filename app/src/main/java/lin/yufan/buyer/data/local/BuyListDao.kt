package lin.yufan.buyer.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import lin.yufan.buyer.data.model.BuyListItem

@Dao
interface BuyListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BuyListItem)

    @Delete
    suspend fun delete(item: BuyListItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: BuyListItem)

    @Query("SELECT * FROM buy_list_items")
    suspend fun getAllBuyListItems(): List<BuyListItem>
}