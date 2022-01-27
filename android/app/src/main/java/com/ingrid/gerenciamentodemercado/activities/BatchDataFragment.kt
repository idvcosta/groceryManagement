package com.ingrid.gerenciamentodemercado.activities

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.ingrid.gerenciamentodemercado.R
import com.ingrid.gerenciamentodemercado.databinding.ActivityRegistryBatchBinding
import com.ingrid.gerenciamentodemercado.databinding.FragmentBatchDataBinding
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.viewModel.RegistryBatchViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory
import java.text.SimpleDateFormat
import java.util.*

class BatchDataFragment : Fragment() {

    lateinit var binding: FragmentBatchDataBinding
    private val viewModel: RegistryBatchViewModel by activityViewModels {
        ViewModelsFactory(requireContext())
    }

    val purchaseDate: Calendar = Calendar.getInstance()
    val expirationDate: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBatchDataBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBindings()
        initViewModel()
    }

    private fun initBindings() {
        val purchaseDateListener = createDateSetListener(purchaseDate)
        updateLabel(binding.etDateSale, purchaseDate)

        binding.etProductName.setOnClickListener {
            viewModel.requestNewProductSelection()
        }

        binding.etDateSale.setOnClickListener {
            DatePickerDialog(
                requireContext(), purchaseDateListener,
                purchaseDate.get(Calendar.YEAR),
                purchaseDate.get(Calendar.MONTH),
                purchaseDate.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val expirationDateListener = createDateSetListener(expirationDate)
        updateLabel(binding.etExpirationDate, expirationDate)

        binding.etExpirationDate.setOnClickListener {
            DatePickerDialog(
                requireContext(), expirationDateListener,
                expirationDate.get(Calendar.YEAR),
                expirationDate.get(Calendar.MONTH),
                expirationDate.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun initViewModel() {
        viewModel.selectedProduct.observe(this, ::updateProduct)
    }

    private fun updateProduct(product: Product) {
        binding.etProductName.setText(product.name)
    }

    private fun createDateSetListener(date: Calendar): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            date.set(Calendar.YEAR, year)
            date.set(Calendar.MONTH, monthOfYear)
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }

    private fun updateLabel(editText: EditText, date: Calendar) {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        editText.setText(sdf.format(date.getTime()))
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BatchDataFragment().apply {

            }
    }
}