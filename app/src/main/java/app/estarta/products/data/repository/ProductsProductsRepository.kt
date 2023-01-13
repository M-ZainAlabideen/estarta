package app.estarta.products.data.repository

import app.estarta.products.domain.repository.IProductsRepository
import app.estarta.remote.DataManager
import com.paulrybitskyi.hiltbinder.BindType
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@BindType
@ViewModelScoped
class ProductsProductsRepository @Inject constructor(private val dataManager: DataManager) : IProductsRepository {
    override fun getListings() = dataManager.getProducts().subscribeOn(Schedulers.io())
}