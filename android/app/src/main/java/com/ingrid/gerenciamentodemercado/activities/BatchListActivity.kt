package com.ingrid.gerenciamentodemercado.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.ingrid.gerenciamentodemercado.R
import com.ingrid.gerenciamentodemercado.adapters.BatchsAdapter
import com.ingrid.gerenciamentodemercado.databinding.ActivityBatchListBinding
import com.ingrid.gerenciamentodemercado.model.Batch
import com.ingrid.gerenciamentodemercado.viewModel.ListBatchViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory

class BatchListActivity : AppCompatActivity() {

    lateinit var binding: ActivityBatchListBinding
    val viewModel: ListBatchViewModel by viewModels { ViewModelsFactory(this) }

    private val selectProductFragment = SelectProductFragment()
    private val selectBatchFragment = SelectBatchFragment()
    val batchDetailsFragment = BatchDetailsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFragment(selectProductFragment)

        viewModel.selectedProduct.observe(this){
            changeFragment(selectBatchFragment)
        }

        viewModel.selectedBatch.observe(this){
            changeFragment(batchDetailsFragment)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_content, fragment)
            .commit()
    }
}