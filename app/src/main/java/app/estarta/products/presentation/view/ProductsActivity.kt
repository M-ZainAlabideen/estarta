package app.estarta.products.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import app.estarta.shared.data.model.Product
import app.estarta.productDetails.presentation.view.ProductDetailsActivity
import app.estarta.products.presentation.adapter.ProductsAdapter
import app.estarta.products.presentation.viewModel.ProductsViewModel
import app.estarta.databinding.ActivityProductsBinding
import app.estarta.util.Constants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsBinding
    private val productsViewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        initRecyclerView()
        observeResults()
        observeLoadingProgress()
        observeFailure()
        productsViewModel.onCreate()

    }

    private fun initRecyclerView() {
        binding.productsRv.apply {
            layoutManager = LinearLayoutManager(this@ProductsActivity)
            adapter = ProductsAdapter(ArrayList(), ::onItemClick)
        }
    }

    private fun observeResults() {
        productsViewModel.results.observe(this) {
            getResultsAdapter()?.updateData(it)
        }
    }


    private fun observeLoadingProgress() {
        productsViewModel.progress.observe(this) { showProgress ->
            if (showProgress) {
                binding.productsRv.showShimmer()
            } else {
                binding.productsRv.hideShimmer()
            }
        }
    }

    private fun getResultsAdapter() =
        binding.productsRv.adapter as? ProductsAdapter


    private fun onItemClick(product: Product) {
        val intent =
            Intent(this@ProductsActivity, ProductDetailsActivity::class.java)
        intent.putExtra(Constants.LIST_ITEM, product)
        startActivity(intent)
    }

    private fun observeFailure() {
        productsViewModel.failure.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

}