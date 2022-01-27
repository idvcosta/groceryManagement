package com.ingrid.gerenciamentodemercado.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.repositories.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistryBatchViewModel(private val repository: ProductsRepository) : ViewModel(){

    private val mutableLoadProducts = MutableLiveData<List<Product>>()
    private val mutableSelectedProduct = MutableLiveData<Product>()
    private val mutableChangeSelectProduct = MutableLiveData<Boolean>()

    val loadProductsResult: LiveData<List<Product>> = mutableLoadProducts
    val selectedProduct: LiveData<Product> = mutableSelectedProduct
    val changeSelectProduct: LiveData<Boolean> = mutableChangeSelectProduct

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            val loadProductsResults = repository.allProducts()
            mutableLoadProducts.postValue(loadProductsResults)
        }
    }

    fun selectProduct(product: Product) {
        mutableSelectedProduct.postValue(product)
    }

    fun requestNewProductSelection() {
        mutableChangeSelectProduct.postValue(true)
    }
}
