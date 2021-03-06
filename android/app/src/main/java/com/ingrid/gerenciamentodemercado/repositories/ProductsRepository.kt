package com.ingrid.gerenciamentodemercado.repositories

import android.content.Context
import androidx.room.Room
import com.ingrid.gerenciamentodemercado.model.Batch
import com.ingrid.gerenciamentodemercado.model.Product
import com.ingrid.gerenciamentodemercado.repositories.database.AppDatabase

class ProductsRepository(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    )
        .build()

    fun addProduct(product: Product) {
        db.productsDAO().addProduct(product)
    }

    fun allProducts(): List<Product> {
        return db.productsDAO().allProducts()
    }

    fun containsProduct(name: String, brand: String): Boolean {
        return db.productsDAO().containsProduct(name, brand)
    }



}
