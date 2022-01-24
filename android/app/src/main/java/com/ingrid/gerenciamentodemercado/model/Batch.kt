package com.ingrid.gerenciamentodemercado.model

import androidx.room.PrimaryKey

data class Batch(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val number:Int,
    val purchasePrice: Double,
    val itemsQuantity:Int
)
