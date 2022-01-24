package com.ingrid.gerenciamentodemercado.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.repositories.AddProductResult
import com.ingrid.gerenciamentodemercado.repositories.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class ProductViewModel(private val repository: ProductsRepository) : ViewModel() {

    val addProductResult = MutableLiveData<AddProductResult>()

    fun addProduct(product: Product) {
        //Muda da main thread para a thread de IO
        viewModelScope.launch(Dispatchers.IO) {
            if (!repository.containsProduct(product.name, product.brand)) {
                repository.addProduct(product)
                addProductResult.postValue(AddProductResult.SUCESS)
            } else {
                addProductResult.postValue(AddProductResult.REPEATED)
            }

        }
    }

}
