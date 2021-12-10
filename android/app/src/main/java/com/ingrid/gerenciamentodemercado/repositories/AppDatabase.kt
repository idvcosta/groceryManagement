package com.ingrid.gerenciamentodemercado.repositories

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ingrid.gerenciamentodemercado.model.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productsDAO(): ProductsDao
}