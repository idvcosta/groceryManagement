package com.ingrid.gerenciamentodemercado.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ingrid.gerenciamentodemercado.databinding.ActivityRegistryProductBinding
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.viewModel.ProductViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory

class RegistryProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistryProductBinding
    private val viewModel: ProductViewModel by viewModels{ViewModelsFactory(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btRegistryProduct.setOnClickListener {
            registryProduct()
        }


    }

    private fun registryProduct() {
        val name = binding.etProductName.text.toString()
        val description = binding.etProductDescription.text.toString()
        val brand = binding.etProductBrand.text.toString()
        val salePrice = binding.etProductSalePrice.text.toString().toDouble()

        val product = Product(name, description, brand, salePrice)
        viewModel.registryProduct(product)
    }
}