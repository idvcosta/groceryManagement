package com.ingrid.gerenciamentodemercado.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.repositories.AddProductResult
import com.ingrid.gerenciamentodemercado.repositories.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistryProductViewModel(private val repository: ProductsRepository) : ViewModel() {

    private val mutableAddProductResult = MutableLiveData<AddProductResult>()
    val addProductResult: LiveData<AddProductResult> = mutableAddProductResult

    fun addProduct(product: Product) {
        //Muda da main thread para a thread de IO
        viewModelScope.launch(Dispatchers.IO) {
            if (!repository.containsProduct(product.name, product.brand)) {
                repository.addProduct(product)
                mutableAddProductResult.postValue(AddProductResult.SUCESS)
            } else {
                mutableAddProductResult.postValue(AddProductResult.REPEATED)
            }
        }
    }


}
