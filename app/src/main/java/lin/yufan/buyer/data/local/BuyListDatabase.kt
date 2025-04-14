package lin.yufan.buyer.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lin.yufan.buyer.data.model.BuyListItem

@Database(
    entities = [BuyListItem::class],
    version = 1
)
abstract class BuyListDatabase : RoomDatabase() {

    abstract fun getBuyListItemDao(): BuyListDao

    companion object {
        @Volatile
        private var instance: BuyListDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BuyListDatabase::class.java,
                "buy_list_db.db"
            ).fallbackToDestructiveMigration().build()
    }
}