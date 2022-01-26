package com.ingrid.gerenciamentodemercado.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ingrid.gerenciamentodemercado.adapters.ProductsAdapter
import com.ingrid.gerenciamentodemercado.databinding.ActivityListBinding
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.viewModel.ListProductsViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory
import kotlin.reflect.KFunction0

class ListActivity : AppCompatActivity() {

    lateinit var binding: ActivityListBinding
    private val viewModel: ListProductsViewModel by viewModels { ViewModelsFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()


    }

    private fun initViewModel() {
        viewModel.loadProductsResult.observe(this, this::updateProducts)

    }

    private fun updateProducts(products: List<Product>) {
        binding.rvProducts.adapter = ProductsAdapter(products)
    }
}