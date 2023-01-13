package app.estarta.remote

import app.estarta.shared.data.model.GetProducts
import app.estarta.util.NetworkUtils
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor() {
    private var apiInterface: ApiInterface =
        ApiHelper.getAPI(NetworkUtils.BASE_URL, ApiInterface::class.java)

    fun getProducts(): Single<GetProducts> {
        return apiInterface.getProducts()
    }

}