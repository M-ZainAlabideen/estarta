package app.estarta.shared.data.model

import app.estarta.util.DateUtils
import java.io.Serializable

data class Product(
    private var created_at: String,
    val image_ids: List<String>,
    val image_urls: List<String>,
    val image_urls_thumbnails: List<String>,
    val name: String,
    val price: String,
    val uid: String
) : Serializable {
    fun getDate(): String = DateUtils.convertToLocalTime(created_at)
}
