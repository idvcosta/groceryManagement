package com.ingrid.gerenciamentodemercado.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ingrid.gerenciamentodemercado.adapters.ProductsAdapter
import com.ingrid.gerenciamentodemercado.databinding.FragmentSelectProductBinding
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.viewModel.AbstractBatchViewModel
import com.ingrid.gerenciamentodemercado.viewModel.RegistryBatchViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory

class SelectProductFragment : Fragment() {

    private lateinit var binding: FragmentSelectProductBinding
    private val viewModel: AbstractBatchViewModel by activityViewModels {
        ViewModelsFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectProductBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.productsList.observe(requireActivity(), this::updateProducts)

    }

    private fun updateProducts(products: List<Product>) {
        binding.rvProducts.adapter = ProductsAdapter(products, ::onProductSelected)
    }

    fun onProductSelected(product: Product) {
        viewModel.selectProduct(product)
    }
}