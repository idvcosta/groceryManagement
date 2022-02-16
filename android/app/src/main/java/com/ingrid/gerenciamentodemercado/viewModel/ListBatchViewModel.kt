package com.ingrid.gerenciamentodemercado.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ingrid.gerenciamentodemercado.model.Batch
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.repositories.BatchRepository
import com.ingrid.gerenciamentodemercado.repositories.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListBatchViewModel(
    private val batchRepository: BatchRepository,
    productRepository: ProductsRepository
) : SelectProductViewModel(productRepository) {

    private val batchs = MutableLiveData<List<Batch>>()
    private val selectedBatch = MutableLiveData<Batch>()

    init {
        loadBatchs()
    }

    fun getBatchs(): LiveData<List<Batch>> = batchs
    fun getSelectedBatch(): LiveData<Batch> = selectedBatch

    private fun loadBatchs() {
        viewModelScope.launch(Dispatchers.IO) {
            val loadBatchsResult = batchRepository.allBatchs()
            batchs.postValue(loadBatchsResult)
        }

    }

    override fun selectProduct(product: Product) {
        super.selectProduct(product)
        viewModelScope.launch(Dispatchers.IO) {
            val batchs = batchRepository.allBatchProduct(product)
            this@ListBatchViewModel.batchs.postValue(batchs)
        }
    }

    fun selectBatch(batch: Batch) {
        selectedBatch.postValue(batch)
    }

}