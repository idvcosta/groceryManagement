package com.ingrid.gerenciamentodemercado.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingrid.gerenciamentodemercado.model.Batch
import com.ingrid.gerenciamentodemercado.repositories.BatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListBatchViewModel(private val repository: BatchRepository): ViewModel() {

    private val mutableLoadBatchs = MutableLiveData<List<Batch>>()
    val loadBatchs : LiveData<List<Batch>> = mutableLoadBatchs

    init {
        loadBatchs()
    }

    private fun loadBatchs() {
        viewModelScope.launch (Dispatchers.IO){
            val loadBatchsResult = repository.allBatchs()
            mutableLoadBatchs.postValue(loadBatchsResult)
        }

    }
}