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

    private val changeSelectProduct = MutableLiveData<Boolean>()

    fun getChangeSelectProduct(): LiveData<Boolean> = changeSelectProduct

    fun requestNewProductSelection() {
        changeSelectProduct.postValue(true)
    }

    fun addBatch(batch: Batch) {
        viewModelScope.launch(Dispatchers.IO) {
            batchRepository.addBatch(batch)
        }
    }
}
