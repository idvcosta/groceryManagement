package com.ingrid.gerenciamentodemercado.repositories

import android.content.Context
import androidx.room.Room
import com.ingrid.gerenciamentodemercado.model.Batch
import com.ingrid.gerenciamentodemercado.repositories.database.AppDatabase

class BatchRepository(context: Context) {

    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    )
        .build()

    fun allBatchs(): List<Batch> {
        return db.batchDAO().allBatch()
    }

    fun addBatch(batch: Batch) {
        db.batchDAO().addBatch(batch)
    }

}
