package lin.yufan.buylist

import android.app.Application
import lin.yufan.buylist.data.local.ShoppingListDatabase
import lin.yufan.buylist.domain.repository.ShoppingListRepository
import lin.yufan.buylist.ui.BuyListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class BuyListApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@BuyListApplication))

        bind() from singleton {
            ShoppingListDatabase(instance())
        }
        bind() from singleton {
            ShoppingListRepository(instance())
        }
        bind() from provider {
            BuyListViewModelFactory(instance())
        }
    }
}