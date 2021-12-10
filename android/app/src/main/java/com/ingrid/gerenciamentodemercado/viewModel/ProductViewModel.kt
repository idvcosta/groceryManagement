package com.ingrid.gerenciamentodemercado.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.repositories.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class ProductViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    fun addProduct(product: Product) {
        viewModelScope.launch (Dispatchers.IO){
            productsRepository.addProduct(product)
        }
    }

}
