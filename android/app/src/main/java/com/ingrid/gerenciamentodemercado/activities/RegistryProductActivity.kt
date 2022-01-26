package com.ingrid.gerenciamentodemercado.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ingrid.gerenciamentodemercado.databinding.ActivityRegistryProductBinding
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.repositories.AddProductResult
import com.ingrid.gerenciamentodemercado.viewModel.RegistryProductViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory

class RegistryProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistryProductBinding
    private val viewModel: RegistryProductViewModel by viewModels { ViewModelsFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()

        binding.btRegistryProduct.setOnClickListener {
            registryProduct()
        }
    }

    private fun initViewModel() {
        viewModel.addProductResult.observe(this) {
            if (it == AddProductResult.SUCESS) {
                clearFields()
                Toast.makeText(this, "Produto cadastrado!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Produto repetido!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun registryProduct() {
        val name = binding.etProductName.text.toString()
        val description = binding.etProductDescription.text.toString()
        val brand = binding.etProductBrand.text.toString()
        val salePriceStr = binding.etProductSalePrice.text.toString()

        if (isValid(name, description, brand, salePriceStr)) {
            if (salePriceStr.contains(",", true)) {
                Toast.makeText(this, "O valor do preço deve ser com ponto!", Toast.LENGTH_LONG)
                    .show()
            } else {
                val salePrice = salePriceStr.toDouble()
                val product = Product(name, description, brand, salePrice)
                viewModel.addProduct(product)
            }
        } else {
            Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        with(binding) {
            etProductName.setText("")
            etProductDescription.setText("")
            etProductBrand.setText("")
            etProductSalePrice.setText("")
        }
    }

    private fun isValid(
        name: String,
        description: String,
        brand: String,
        salePrice: String
    ) =
        !name.isNullOrBlank()
                && !description.isNullOrBlank()
                && !brand.isNullOrBlank()
                && isValidPrice(salePrice)

    private fun isValidPrice(price: String): Boolean {
        return price.toDoubleOrNull() != null
    }
}