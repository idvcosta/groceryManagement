package com.ingrid.gerenciamentodemercado.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ingrid.gerenciamentodemercado.databinding.ActivityRegistryLotBinding

class RegistryLotActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistryLotBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryLotBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}