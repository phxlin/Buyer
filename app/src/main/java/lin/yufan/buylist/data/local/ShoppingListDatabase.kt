package lin.yufan.buylist.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lin.yufan.buylist.data.model.ShoppingListItem

@Database(
    entities = [ShoppingListItem::class],
    version = 1
)
abstract class ShoppingListDatabase : RoomDatabase() {

    abstract fun getShoppingListDao(): ShoppingListDao

    companion object {
        @Volatile
        private var instance: ShoppingListDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingListDatabase::class.java,
                "BuyListDB.db"
            ).build()
    }
}