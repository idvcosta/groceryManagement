package com.ingrid.gerenciamentodemercado.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import com.ingrid.gerenciamentodemercado.databinding.ActivityRegistryBatchBinding
import com.ingrid.gerenciamentodemercado.ui.fragments.BatchDataFragment
import com.ingrid.gerenciamentodemercado.ui.fragments.SelectProductFragment
import com.ingrid.gerenciamentodemercado.viewModel.RegistryBatchViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory

class RegistryBatchActivity : BaseActivity() {
    lateinit var binding: ActivityRegistryBatchBinding
    val viewModel: RegistryBatchViewModel by viewModels { ViewModelsFactory(this) }

    private val selectProductFragment = SelectProductFragment()
    private val batchDataFragment = BatchDataFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryBatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFragment(selectProductFragment)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getSelectedProduct().observe(this) {
            changeFragment(batchDataFragment)
        }

        viewModel.getChangeSelectProduct().observe(this) {
            changeFragment(selectProductFragment)
        }
    }
}