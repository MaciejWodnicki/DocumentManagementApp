package com.example.warehouseOrderApp.src.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.warehouseOrderApp.src.data.DocumentEntryCrossRef

@Dao
interface DocumentEntryCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(documentEntryCrossRef: DocumentEntryCrossRef)
}