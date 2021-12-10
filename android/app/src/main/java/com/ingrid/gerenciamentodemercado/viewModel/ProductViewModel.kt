package com.ingrid.gerenciamentodemercado.viewModel

import androidx.lifecycle.ViewModel
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.model.ProductsRepository

open class ProductViewModel( val productsRepository: ProductsRepository) : ViewModel() {
    // chamada para repository


    fun registryProduct(product: Product) {

        TODO("Not yet implemented")
    }

}
