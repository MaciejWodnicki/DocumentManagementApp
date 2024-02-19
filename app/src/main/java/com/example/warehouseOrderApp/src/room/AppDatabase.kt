package com.example.warehouseOrderApp.src.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.warehouseOrderApp.src.data.Contractor
import com.example.warehouseOrderApp.src.data.Document
import com.example.warehouseOrderApp.src.data.DocumentEntryCrossRef
import com.example.warehouseOrderApp.src.data.Entry

@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [Contractor::class, Entry::class, Document::class, DocumentEntryCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase:RoomDatabase() {
    abstract fun contractorDao():ContractorDao
    abstract fun documentsDao():DocumentsDao

    companion object{
        @Volatile
        var INSTANCE:AppDatabase? = null
        fun getDatabase(context: Context):AppDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}