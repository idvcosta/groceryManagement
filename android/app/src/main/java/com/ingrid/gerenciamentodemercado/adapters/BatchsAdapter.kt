package com.ingrid.gerenciamentodemercado.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.Consumer
import androidx.recyclerview.widget.RecyclerView
import com.ingrid.gerenciamentodemercado.databinding.RowBatchBinding
import com.ingrid.gerenciamentodemercado.model.Batch

class BatchsAdapter(
    private val batchs: List<Batch>,
    private val selectBatchCallback: Consumer<Batch>? = null
) :
    RecyclerView.Adapter<BatchsAdapter.BatchHolder>() {

    class BatchHolder(val batchRow: RowBatchBinding) :
        RecyclerView.ViewHolder(batchRow.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BatchHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val batchRow = RowBatchBinding.inflate(inflater)

        batchRow.root.setOnClickListener { view ->
            val batch = view.tag as Batch
            selectBatchCallback?.accept(batch)
        }

        return BatchHolder(batchRow)
    }

    override fun onBindViewHolder(holder: BatchHolder, position: Int) {
        val batch = batchs[position]

        holder.batchRow.tvBatchItem.text = batch.number.toString()
        holder.batchRow.root.tag = batch
    }

    override fun getItemCount(): Int {
        return batchs.size
    }

}
