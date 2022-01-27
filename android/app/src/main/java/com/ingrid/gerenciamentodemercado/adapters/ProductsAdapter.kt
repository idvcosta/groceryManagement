package com.ingrid.gerenciamentodemercado.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ingrid.gerenciamentodemercado.databinding.RowProductBinding
import com.ingrid.gerenciamentodemercado.model.Product


class ProductsAdapter(
    private val products: List<Product>,
    private val selectProductCallback: ((product: Product) -> Unit)? = null
) :
    RecyclerView.Adapter<ProductsAdapter.ProductHolder>() {

    class ProductHolder(val productRow: RowProductBinding) :
        RecyclerView.ViewHolder(productRow.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val productRow = RowProductBinding.inflate(inflater)

        productRow.root.setOnClickListener { view ->
            val product = view.tag as Product
            selectProductCallback?.invoke(product)
        }

        return ProductHolder(productRow)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = products[position]

        holder.productRow.tvProductItem.text = product.name
        holder.productRow.root.tag = product
    }

    override fun getItemCount(): Int {
        return products.size
    }

}
