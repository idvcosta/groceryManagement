package com.ingrid.gerenciamentodemercado.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ingrid.gerenciamentodemercado.R
import com.ingrid.gerenciamentodemercado.databinding.FragmentBatchDetailsBinding
import com.ingrid.gerenciamentodemercado.model.Batch
import com.ingrid.gerenciamentodemercado.viewModel.ListBatchViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory

class BatchDetailsFragment : Fragment() {

    lateinit var binding: FragmentBatchDetailsBinding
    lateinit var batchByProduct: Batch
    val viewModel: ListBatchViewModel by activityViewModels { ViewModelsFactory(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBatchDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.selectedBatch.observe(requireActivity(), ::updateBatch)
    }

    private fun updateBatch(batch: Batch){
        val productName = viewModel.selectedProduct.value?.name

        val batchNumberStr = batch.number.toString()
        val dateSaleStr = batch.saleDate.toString()
        val expirationDateStr = batch.expirationDate.toString()
        val itemsQuantityStr = batch.itemsQuantity.toString()
        val purchasePriceStr = batch.purchasePrice.toString()

        batchByProduct = batch
        binding.tvProductName.text = "Produto: $productName"
        binding.tvBatchNumber.text = "Nº do Lote: $batchNumberStr"
        binding.tvDateSale.text = "Data da compra: $dateSaleStr"
        binding.tvExpirationDate.text = "Data de expiração: $expirationDateStr"
        binding.tvQtdProduct.text = "Quantidade de produto: $itemsQuantityStr"
        binding.tvPurchaseSale.text = "Preço de venda: $purchasePriceStr"
    }
}