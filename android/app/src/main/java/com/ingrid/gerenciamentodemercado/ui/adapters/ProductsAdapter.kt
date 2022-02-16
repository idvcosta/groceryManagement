package com.ingrid.gerenciamentodemercado.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.Consumer
import androidx.recyclerview.widget.RecyclerView
import com.ingrid.gerenciamentodemercado.databinding.RowProductBinding
import com.ingrid.gerenciamentodemercado.model.Product

class ProductsAdapter(
    private val selectProductCallback: Consumer<Product>? = null
) :
    RecyclerView.Adapter<ProductsAdapter.ProductHolder>() {

    class ProductHolder(val productRow: RowProductBinding) :
        RecyclerView.ViewHolder(productRow.root)

    private var products: List<Product>? = null

    fun updateProducts(products: List<Product>){
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val productRow = RowProductBinding.inflate(inflater)

        productRow.root.setOnClickListener { view ->
            val product = view.tag as Product
            selectProductCallback?.accept(product)
        }

        return ProductHolder(productRow)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        products?.let { products ->
            val product = products[position]

            holder.productRow.tvProductItem.text = product.name
            holder.productRow.root.tag = product
        }
    }

    override fun getItemCount() = products?.size ?: 0
}
