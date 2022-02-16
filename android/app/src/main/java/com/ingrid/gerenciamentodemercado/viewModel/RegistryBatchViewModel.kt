package com.ingrid.gerenciamentodemercado.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ingrid.gerenciamentodemercado.model.Batch
import com.ingrid.gerenciamentodemercado.repositories.BatchRepository
import com.ingrid.gerenciamentodemercado.repositories.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistryBatchViewModel(
    productRepository: ProductsRepository,
    private val batchRepository: BatchRepository
) : SelectProductViewModel(productRepository) {

    private val mutableChangeSelectProduct = MutableLiveData<Boolean>()

    val changeSelectProduct: LiveData<Boolean> = mutableChangeSelectProduct

    fun requestNewProductSelection() {
        mutableChangeSelectProduct.postValue(true)
    }

    fun addBatch(batch: Batch) {
        viewModelScope.launch(Dispatchers.IO) {
            batchRepository.addBatch(batch)
        }
    }
}
