package com.example.warehouseOrderApp.src.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.warehouseOrderApp.src.repositories.DocumentsService
import java.time.LocalDate

@Entity(tableName = "Documents")
class Document(
    documentSymbol: String = "",
    documentDate: LocalDate = LocalDate.of(2012, 12, 22),
    contractorId: Long = 0,
) {
    @ColumnInfo("document_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    @ColumnInfo("document_symbol")
    var symbol: String = documentSymbol

    @ColumnInfo("document_date")
    var date: LocalDate = documentDate

    @ColumnInfo("document_contractor_id")
    var contractor: Long = contractorId

    fun update(
        documentSymbol: String = "",
        documentDate: LocalDate = LocalDate.of(2012, 12, 22),
        contractorId: Long,
    ) {
        symbol = documentSymbol
        date = documentDate
        contractor = contractorId
    }

}