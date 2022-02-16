package com.ingrid.gerenciamentodemercado.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import com.ingrid.gerenciamentodemercado.databinding.ActivityListBinding
import com.ingrid.gerenciamentodemercado.ui.fragments.ProductDetailsFragment
import com.ingrid.gerenciamentodemercado.ui.fragments.SelectProductFragment
import com.ingrid.gerenciamentodemercado.viewModel.ListProductsViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory

class ProductsListActivity : BaseActivity() {

    lateinit var binding: ActivityListBinding
     val viewModel: ListProductsViewModel by viewModels { ViewModelsFactory(this) }


    private val selectProductFragment = SelectProductFragment()
    private val productDetailsFragment = ProductDetailsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFragment(selectProductFragment)

        viewModel.getSelectedProduct().observe(this){
            changeFragment(productDetailsFragment)
        }
    }
}