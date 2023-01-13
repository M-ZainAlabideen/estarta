package app.estarta.products.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.estarta.shared.data.model.GetProducts
import app.estarta.products.domain.repository.IProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import app.estarta.shared.data.model.Product
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private var repository: IProductsRepository,
) : ViewModel() {
    val results = MutableLiveData<List<Product>>()
    val failure = MutableLiveData<String>()
    val progress = MutableLiveData(false)

    private val compositeDisposable = CompositeDisposable()

    fun onCreate() {
        getListings()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.apply {
            if (!isDisposed) {
                dispose()
            }
        }
    }

    private fun getListings() {
        progress.value = true
        compositeDisposable.add(
            repository.getListings()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::handleResults, ::handleError)
        )
    }


    private fun handleResults(response: GetProducts) {
        progress.value = false
        if (response.products.isNotEmpty()) {
            results.value = response.products
        }
    }

    private fun handleError(throwable: Throwable) {
        failure.value = throwable.message
        progress.value = false
    }
}