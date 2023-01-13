package app.estarta.productDetails.presentation.view

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import app.estarta.R
import app.estarta.databinding.ActivityProductDetailsBinding
import app.estarta.shared.data.model.Product
import app.estarta.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        setupToolbar()
        receiveBundle()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = getString(R.string.details)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun receiveBundle() {
        val extras = intent.extras
        if (extras != null) {
            val product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                extras.getSerializable(Constants.LIST_ITEM, Product::class.java)
            } else {
                @Suppress("DEPRECATION") extras.getSerializable(Constants.LIST_ITEM)
            }
            setData(product as Product)
        }
    }

    private fun setData(product: Product) {
        binding.apply {
            date.text = product.getDate()
            name.text = product.name
            price.text = product.price
        }
    }

}