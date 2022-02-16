package com.ingrid.gerenciamentodemercado.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.repositories.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListProductsViewModel(private val repository: ProductsRepository) : SelectProductViewModel(repository) {

    private val loadProducts = MutableLiveData<List<Product>>()

    init {
        loadProducts()
    }

    fun getloadProductsResult(): LiveData<List<Product>> = loadProducts

    private fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            val loadProductsResults = repository.allProducts()
            loadProducts.postValue(loadProductsResults)
        }
    }
}