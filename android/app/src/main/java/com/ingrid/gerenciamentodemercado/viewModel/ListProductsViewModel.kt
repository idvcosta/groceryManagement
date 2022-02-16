package com.ingrid.gerenciamentodemercado.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.repositories.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListProductsViewModel(private val repository: ProductsRepository) : SelectProductViewModel(repository) {

    private val mutableLoadProducts = MutableLiveData<List<Product>>()
    val loadProductsResult: LiveData<List<Product>> = mutableLoadProducts

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            val loadProductsResults = repository.allProducts()
            mutableLoadProducts.postValue(loadProductsResults)
        }
    }
}