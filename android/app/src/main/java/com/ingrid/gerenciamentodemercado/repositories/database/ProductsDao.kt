package com.ingrid.gerenciamentodemercado.repositories.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ingrid.gerenciamentodemercado.model.Product

@Dao
interface ProductsDao {

    @Query("SELECT * FROM product")
    fun allProducts(): List<Product>

    @Insert
    fun addProduct(product: Product)

    @Query("SELECT * FROM product WHERE name = :name COLLATE NOCASE AND brand = :brand COLLATE NOCASE")
    fun containsProduct(name: String, brand: String): Boolean
}