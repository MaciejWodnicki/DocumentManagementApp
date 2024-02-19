package com.example.warehouseOrderApp.src.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.warehouseOrderApp.src.data.Entry
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: Entry): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entry: Entry)

    @Query("SELECT * FROM Entry " +
            "JOIN DocumentEntryCrossRef AS CR ON CR.entryId = entry_id " +
            "WHERE CR.documentId=:documentId")
    fun getDocumentEntries(documentId: Long): Flow<List<Entry>>

    @Query("SELECT * FROM ENTRY " +
            "WHERE entry_id=:entryId")
    fun getEntry(entryId:Long): Flow<Entry>
}