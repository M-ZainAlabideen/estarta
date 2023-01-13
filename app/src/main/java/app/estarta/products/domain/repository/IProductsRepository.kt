package app.estarta.products.domain.repository

import app.estarta.shared.data.model.GetProducts
import io.reactivex.Single

interface IProductsRepository {
    fun getListings(): Single<GetProducts>
}