package com.ingrid.gerenciamentodemercado.repositories.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ingrid.gerenciamentodemercado.model.Batch
import com.ingrid.gerenciamentodemercado.model.Product

// essa classe trata da criação todas as entidades qeu serão usadas no sistema
@Database(entities = [Product::class, Batch::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productsDAO(): ProductsDao
    abstract fun batchDAO(): BatchDao
}

