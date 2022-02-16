package com.ingrid.gerenciamentodemercado.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.repositories.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class SelectProductViewModel(
    private val productRepository: ProductsRepository
) : ViewModel() {

    private val productsList = MutableLiveData<List<Product>>()
    private val selectedProduct = MutableLiveData<Product>()

    init {
        loadProducts()
    }

    fun getProductsList(): LiveData<List<Product>> = productsList
    fun getSelectedProduct(): LiveData<Product> = selectedProduct

    private fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            val loadProductsResults = productRepository.allProducts()
            productsList.postValue(loadProductsResults)
        }
    }

    open fun selectProduct(product: Product) {
        selectedProduct.postValue(product)
    }
}