package com.ingrid.gerenciamentodemercado.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ingrid.gerenciamentodemercado.databinding.FragmentProductDetailsBinding
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.viewModel.SelectProductViewModel
import com.ingrid.gerenciamentodemercado.viewModel.ViewModelsFactory


class ProductDetailsFragment : Fragment() {

    lateinit var binding: FragmentProductDetailsBinding
    val viewModel: SelectProductViewModel by activityViewModels { ViewModelsFactory(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getSelectedProduct().observe(requireActivity(), ::updateProduct)
    }

    private fun updateProduct(product: Product) {
        val priceStr = product.salePrice.toString()

        binding.tvProductName.text = "Nome: ${product.name}"
        binding.tvProductDescription.text = "Descrição: ${product.description}"
        binding.tvProductBrand.text = "Marca: ${product.brand}"
        binding.tvProductSalePrice.text = "Preço de venda: ${priceStr}"

    }

}