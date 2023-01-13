package app.estarta.shared.data.model

import com.google.gson.annotations.SerializedName

data class GetProducts(
    @SerializedName("results")
    val products: List<Product>
)