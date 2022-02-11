package com.ingrid.gerenciamentodemercado.repositories.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ingrid.gerenciamentodemercado.model.Batch

@Dao
interface BatchDao {

    @Insert
    fun addBatch(batch: Batch)

    @Query("SELECT * FROM batch")
    fun allBatch():List<Batch>

    @Query("SELECT * FROM batch WHERE productId == :productId")
    fun filterBatchByProducts(productId: Int): List<Batch>
}