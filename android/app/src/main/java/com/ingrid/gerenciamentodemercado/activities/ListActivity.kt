package com.ingrid.gerenciamentodemercado.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ingrid.gerenciamentodemercado.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}