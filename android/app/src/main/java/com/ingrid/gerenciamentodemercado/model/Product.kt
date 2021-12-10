package com.ingrid.gerenciamentodemercado.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val description: String,
    val brand: String,
    val salePrice: Double
) {
    constructor(
        name: String,
        description: String,
        brand: String,
        salePrice: Double
    ) : this(null, name, description, brand, salePrice)
}
