package com.example.warehouseOrderApp.src.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.warehouseOrderApp.src.data.Document
import com.example.warehouseOrderApp.src.data.Entry
import kotlinx.coroutines.flow.Flow

@Dao
interface DocumentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(document: Document)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(document: Document)

    @Delete
    suspend fun delete(document: Document)

    @Query("Select * FROM documents")
    fun getAllDocuments(): Flow<MutableList<Document>>

    @Query("Select * FROM documents WHERE document_id =:documentId")
    fun getDocument(documentId:Long): Flow<Document>

    @Query("Select * From entry " +
            "JOIN documententrycrossref on entryId = entry_id " +
            "JOIN documents on document_id = documentId " +
            "WHERE documentId =:targetDocumentId")
    fun getEntries(targetDocumentId: Long): Flow<MutableList<Entry>>

}