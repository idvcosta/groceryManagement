package com.ingrid.gerenciamentodemercado.activities

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.ingrid.gerenciamentodemercado.databinding.FragmentBatchDataBinding
import com.ingrid.gerenciamentodemercado.model.Batch
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
    private lateinit var batchProduct: Product

    private val purchaseDate: Calendar = Calendar.getInstance()
    private val expirationDate: Calendar = Calendar.getInstance()

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

        binding.btRegistryBatch.setOnClickListener {
            registryBatch()
        }
    }

    private fun initBindings() {
        binding.etProductName.setOnClickListener {
            viewModel.requestNewProductSelection()
        }

        val purchaseDateListener = createDateSetListener(purchaseDate, binding.etDateSale)

        binding.etDateSale.setOnClickListener {
            DatePickerDialog(
                requireContext(), purchaseDateListener,
                purchaseDate.get(Calendar.YEAR),
                purchaseDate.get(Calendar.MONTH),
                purchaseDate.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val expirationDateListener = createDateSetListener(expirationDate, binding.etExpirationDate)

        binding.etExpirationDate.setOnClickListener {
            DatePickerDialog(
                requireContext(), expirationDateListener,
                expirationDate.get(Calendar.YEAR),
                expirationDate.get(Calendar.MONTH),
                expirationDate.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun createDateSetListener(
        date: Calendar,
        etDate: EditText
    ): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            date.set(Calendar.YEAR, year)
            date.set(Calendar.MONTH, monthOfYear)
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            updateLabel(etDate, date)
        }
    }

    private fun updateLabel(editText: EditText, date: Calendar) {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        editText.setText(sdf.format(date.getTime()))
    }

    private fun initViewModel() {
        viewModel.selectedProduct.observe(requireActivity(), ::updateProduct)
    }

    private fun updateProduct(product: Product) {
        batchProduct = product
        binding.etProductName.setText(product.name)
    }

    private fun registryBatch() {
        val batchNumber = binding.etBatchNumber.text.toString().toInt()
        val dateSale = purchaseDate.time
        val qtdProducts = binding.etQtdProduct.text.toString().toInt()
        val dateExpiration = expirationDate.time
        val purchaseSaleStr = binding.etPurchaseSale.text.toString()

        val purchaseSale = purchaseSaleStr.toDouble()

        batchProduct.id?.let { productId ->
            val batch =
                Batch(productId, batchNumber, dateSale, qtdProducts, dateExpiration, purchaseSale)

            viewModel.addBatch(batch)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BatchDataFragment().apply {

            }
    }
}