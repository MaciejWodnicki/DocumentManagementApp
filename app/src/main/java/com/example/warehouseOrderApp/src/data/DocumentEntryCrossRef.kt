package com.example.warehouseOrderApp.src.data

import androidx.room.Entity

@Entity(primaryKeys = ["entryId","documentId"])
data class DocumentEntryCrossRef (
    val entryId: Long,
    val documentId: Long
)