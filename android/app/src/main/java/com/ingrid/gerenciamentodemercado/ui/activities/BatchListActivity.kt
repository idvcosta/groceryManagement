package com.ingrid.gerenciamentodemercado.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import com.ingrid.gerenciamentodemercado.databinding.ActivityBatchListBinding
import com.ingrid.gerenciamentodemercado.ui.fragments.BatchDetailsFragment
import com.ingrid.gerenciamentodemercado.ui.fragments.SelectBatchFragment
import com.ingrid.gerenciamentodemercado.ui.fragments.SelectProductFragment
import com.ingrid.gerenciamentodemercado.viewModel.ListBatchViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory

class BatchListActivity : BaseActivity() {

    lateinit var binding: ActivityBatchListBinding
    val viewModel: ListBatchViewModel by viewModels { ViewModelsFactory(this) }

    private val selectProductFragment = SelectProductFragment()
    private val selectBatchFragment = SelectBatchFragment()
    private val batchDetailsFragment = BatchDetailsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFragment(selectProductFragment)

        viewModel.getSelectedProduct().observe(this) {
            changeFragment(selectBatchFragment)
        }

        viewModel.getSelectedBatch().observe(this) {
            changeFragment(batchDetailsFragment)
        }
    }
}