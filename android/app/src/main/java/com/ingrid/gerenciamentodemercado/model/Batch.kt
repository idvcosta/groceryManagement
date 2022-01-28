package com.ingrid.gerenciamentodemercado.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("productId"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Batch(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val productId: Int,
    val number: Int,
    val saleDate: Date,
    val itemsQuantity: Int,
    val expirationDate: Date,
    val purchasePrice: Double
) {
    @Ignore
    constructor(
        productId: Int,
        number: Int,
        saleDate: Date,
        itemsQuantity: Int,
        expirationDate: Date,
        purchasePrice: Double
    ) : this(null, productId, number, saleDate, itemsQuantity, expirationDate, purchasePrice)
}
