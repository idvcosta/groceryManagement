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
) : AbstractBatchViewModel(productRepository) {

    private val mutableBatchs = MutableLiveData<List<Batch>>()
    private val mutableSelectedBatch = MutableLiveData<Batch>()

    val batchs: LiveData<List<Batch>> = mutableBatchs
    val selectedBatch: LiveData<Batch> = mutableSelectedBatch

    init {
        loadBatchs()
    }

    private fun loadBatchs() {
        viewModelScope.launch(Dispatchers.IO) {
            val loadBatchsResult = batchRepository.allBatchs()
            mutableBatchs.postValue(loadBatchsResult)
        }

    }

    override fun selectProduct(product: Product){
        super.selectProduct(product)
        viewModelScope.launch(Dispatchers.IO) {
            val batchs = batchRepository.allBatchProduct(product)
            mutableBatchs.postValue(batchs)
        }
    }
}