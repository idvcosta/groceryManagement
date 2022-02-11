package com.ingrid.gerenciamentodemercado.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ingrid.gerenciamentodemercado.R
import com.ingrid.gerenciamentodemercado.databinding.ActivityRegistryBatchBinding
import com.ingrid.gerenciamentodemercado.viewModel.RegistryBatchViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory

class RegistryBatchActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistryBatchBinding
    val viewModel: RegistryBatchViewModel by viewModels { ViewModelsFactory(this) }

    private val selectProductFragment = SelectProductFragment()
    private val batchDataFragment = BatchDataFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryBatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFragment(selectProductFragment)

        viewModel.selectedProduct.observe(this) {
            changeFragment(batchDataFragment)
        }

        viewModel.changeSelectProduct.observe(this) {
            changeFragment(selectProductFragment)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_content, fragment)
            .commit()
    }

}