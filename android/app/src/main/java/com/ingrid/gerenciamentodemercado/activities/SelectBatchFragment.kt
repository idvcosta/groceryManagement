package com.ingrid.gerenciamentodemercado.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ingrid.gerenciamentodemercado.R
import com.ingrid.gerenciamentodemercado.adapters.BatchsAdapter
import com.ingrid.gerenciamentodemercado.databinding.FragmentSelectBatchBinding
import com.ingrid.gerenciamentodemercado.model.Batch
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.viewModel.ListBatchViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory

class SelectBatchFragment : Fragment() {

    lateinit var binding: FragmentSelectBatchBinding
    private lateinit var batchProduct: Product


    private val viewModel: ListBatchViewModel by activityViewModels {
        ViewModelsFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSelectBatchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.selectedProduct.observe(requireActivity(), ::updateProduct)
        viewModel.batchs.observe(requireActivity(), ::updateBatchs)
    }

    private fun updateProduct(product: Product) {
        batchProduct = product
        binding.tvProductName.text = "Lotes de ${product.name}"
    }

    private fun updateBatchs(batchs: List<Batch>) {
        binding.rvBatchs.adapter = BatchsAdapter(batchs, ::onBatchSelected)
    }

    fun onBatchSelected(batch: Batch){
        viewModel.selectBatch(batch)
    }

}