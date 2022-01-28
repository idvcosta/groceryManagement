package com.ingrid.gerenciamentodemercado.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ingrid.gerenciamentodemercado.R
import com.ingrid.gerenciamentodemercado.adapters.BatchsAdapter
import com.ingrid.gerenciamentodemercado.databinding.ActivityBatchListBinding
import com.ingrid.gerenciamentodemercado.model.Batch
import com.ingrid.gerenciamentodemercado.viewModel.ListBatchViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory

class BatchListActivity : AppCompatActivity() {

    lateinit var binding: ActivityBatchListBinding
    val viewModel: ListBatchViewModel by viewModels { ViewModelsFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.loadBatchs.observe(this, this::updateBatchs)
    }

    private fun updateBatchs(batchs: List<Batch>) {
        binding.rvBatchs.adapter = BatchsAdapter(batchs)
    }
}