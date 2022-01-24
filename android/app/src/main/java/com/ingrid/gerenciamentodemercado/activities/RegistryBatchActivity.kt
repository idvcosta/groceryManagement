package com.ingrid.gerenciamentodemercado.activities

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.ingrid.gerenciamentodemercado.databinding.ActivityRegistryBatchBinding
import java.text.SimpleDateFormat
import java.util.*


class RegistryBatchActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistryBatchBinding
//    val viewModel: LotViewModel by viewModels { ViewModelsFactory(this) }

    val purchaseDate: Calendar = Calendar.getInstance()
    val expirationDate: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryBatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBindings()
    }

    private fun createDateSetListener(date: Calendar): OnDateSetListener {
        return OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            date.set(Calendar.YEAR, year)
            date.set(Calendar.MONTH, monthOfYear)
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }

    private fun initBindings() {
        val purchaseDateListener = createDateSetListener(purchaseDate)
        updateLabel(binding.etDateSale, purchaseDate)


        binding.etDateSale.setOnClickListener {
            DatePickerDialog(this, purchaseDateListener,
                    purchaseDate.get(Calendar.YEAR),
                    purchaseDate.get(Calendar.MONTH),
                    purchaseDate.get(Calendar.DAY_OF_MONTH)).show()
        }

        val expirationDateListener = createDateSetListener(expirationDate)
        updateLabel(binding.etExpirationDate, expirationDate)

        binding.etExpirationDate.setOnClickListener {
            DatePickerDialog(this, expirationDateListener,
                    expirationDate.get(Calendar.YEAR),
                    expirationDate.get(Calendar.MONTH),
                    expirationDate.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun updateLabel(editText: EditText, date: Calendar) {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        editText.setText(sdf.format(date.getTime()))
    }

}