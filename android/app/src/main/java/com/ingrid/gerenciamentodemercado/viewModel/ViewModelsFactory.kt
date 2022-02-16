package com.ingrid.gerenciamentodemercado.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ingrid.gerenciamentodemercado.repositories.BatchRepository
import com.ingrid.gerenciamentodemercado.repositories.ProductsRepository
import com.ingrid.gerenciamentodemercado.ui.activities.BatchListActivity
import com.ingrid.gerenciamentodemercado.ui.activities.ProductsListActivity
import com.ingrid.gerenciamentodemercado.ui.activities.RegistryBatchActivity

class ViewModelsFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == RegistryProductViewModel::class.java) {
            return RegistryProductViewModel(ProductsRepository(context)) as T
        }
        if (modelClass == ListProductsViewModel::class.java) {
            return ListProductsViewModel(ProductsRepository(context)) as T
        }
        if (modelClass == ListBatchViewModel::class.java) {
            return ListBatchViewModel(BatchRepository(context), ProductsRepository(context)) as T
        }
        if (modelClass == RegistryBatchViewModel::class.java) {
            return RegistryBatchViewModel(
                ProductsRepository(context),
                BatchRepository(context)
            ) as T
        }
        if (modelClass == SelectProductViewModel::class.java) {
            when (context) {
                is BatchListActivity -> {
                    return context.viewModel as T
                }
                is RegistryBatchActivity -> {
                    return context.viewModel as T
                }
                is ProductsListActivity -> {
                    return context.viewModel as T
                }
            }
        }

        throw IllegalStateException("Not Implemented for $modelClass")
    }
}