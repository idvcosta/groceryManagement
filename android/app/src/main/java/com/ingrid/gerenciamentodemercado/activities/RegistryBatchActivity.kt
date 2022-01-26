package com.ingrid.gerenciamentodemercado.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ingrid.gerenciamentodemercado.R
import com.ingrid.gerenciamentodemercado.databinding.ActivityRegistryBatchBinding
import com.ingrid.gerenciamentodemercado.viewModel.RegistryBatchViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory


class RegistryBatchActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistryBatchBinding
    val viewModel: RegistryBatchViewModel by viewModels {ViewModelsFactory(this)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryBatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_content, SelectProductFragment())
            //.addToBackStack("selectProduct")
            .commit()

//        viewModel.productSelectedSignal.observe(this){
//            supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.fragment_content, BatchDataFragment())
//                .commit()
//        }
    }

}