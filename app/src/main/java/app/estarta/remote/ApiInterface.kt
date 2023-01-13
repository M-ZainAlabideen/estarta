package app.estarta.remote

import app.estarta.shared.data.model.GetProducts
import app.estarta.util.NetworkUtils
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET(NetworkUtils.PRODUCTS)
    fun getProducts(): Single<GetProducts>

}