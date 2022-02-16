package com.ingrid.gerenciamentodemercado.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ingrid.gerenciamentodemercado.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivities()

    }

    private fun initActivities() {
        binding.btScreenRegistryProduct.setOnClickListener {
            val intent = Intent(this, RegistryProductActivity::class.java)
            startActivity(intent)
        }

        binding.btScreenProductsList.setOnClickListener {
            val intent = Intent(this, ProductsListActivity::class.java)
            startActivity(intent)
        }

        binding.btScreenRegistryLot.setOnClickListener {
            val intent = Intent(this, RegistryBatchActivity::class.java)
            startActivity(intent)
        }

        binding.btScreenBatchsList.setOnClickListener {
            val intent = Intent(this, BatchListActivity::class.java)
            startActivity(intent)
        }
    }
}