package com.ingrid.gerenciamentodemercado.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.repositories.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class AbstractBatchViewModel(
    private val productRepository: ProductsRepository
) : ViewModel() {

    private val mutableListProducts = MutableLiveData<List<Product>>()
    private val mutableSelectedProduct = MutableLiveData<Product>()

    val productsList: LiveData<List<Product>> = mutableListProducts
    val selectedProduct: LiveData<Product> = mutableSelectedProduct

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            val loadProductsResults = productRepository.allProducts()
            mutableListProducts.postValue(loadProductsResults)
        }
    }

    open fun selectProduct(product: Product) {
        mutableSelectedProduct.postValue(product)
    }


}