package com.ingrid.gerenciamentodemercado.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ingrid.gerenciamentodemercado.ui.adapters.ProductsAdapter
import com.ingrid.gerenciamentodemercado.databinding.FragmentSelectProductBinding
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.viewModel.SelectProductViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory

class SelectProductFragment : Fragment() {

    private lateinit var binding: FragmentSelectProductBinding
    private val viewModel: SelectProductViewModel by activityViewModels {
        ViewModelsFactory(requireContext())
    }
    private val productsAdapter = ProductsAdapter(::onProductSelected)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectProductBinding.inflate(inflater, container, false)
        binding.rvProducts.adapter = productsAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getProductsList().observe(requireActivity(), this::updateProducts)
    }

    private fun updateProducts(products: List<Product>) {
        productsAdapter.updateProducts(products)
    }

    private fun onProductSelected(product: Product) {
        viewModel.selectProduct(product)
    }
}