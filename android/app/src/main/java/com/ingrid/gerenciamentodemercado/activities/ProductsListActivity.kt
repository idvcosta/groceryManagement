package com.ingrid.gerenciamentodemercado.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.ingrid.gerenciamentodemercado.R
import com.ingrid.gerenciamentodemercado.adapters.ProductsAdapter
import com.ingrid.gerenciamentodemercado.databinding.ActivityListBinding
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.viewModel.ListProductsViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory
import kotlin.reflect.KFunction0

class ProductsListActivity : AppCompatActivity() {

    lateinit var binding: ActivityListBinding
     val viewModel: ListProductsViewModel by viewModels { ViewModelsFactory(this) }


    private val selectProductFragment = SelectProductFragment()
    private val productDetailsFragment = ProductDetailsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFragment(selectProductFragment)

        viewModel.selectedProduct.observe(this){
            changeFragment(productDetailsFragment)
            //Toast.makeText(this,"ir para o detalhe",Toast.LENGTH_LONG).show()
        }

        //initViewModel()
    }

//    private fun initViewModel() {
//        viewModel.loadProductsResult.observe(this, this::updateProducts)
//    }
//
//    private fun updateProducts(products: List<Product>) {
//        // TODO revisit this
//        binding.rvProducts.adapter = ProductsAdapter(products)
//    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_content, fragment)
            .commit()
    }
}