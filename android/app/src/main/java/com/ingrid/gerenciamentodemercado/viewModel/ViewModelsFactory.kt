package com.ingrid.gerenciamentodemercado.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ingrid.gerenciamentodemercado.model.ProductsRepository

class ViewModelsFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductViewModel(ProductsRepository(context)) as T
    }
}