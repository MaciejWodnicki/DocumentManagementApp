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
    contractorId: Int = -1,
) {
    @ColumnInfo("document_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    @ColumnInfo("document_symbol")
    var symbol: String = documentSymbol

    @ColumnInfo("document_date")
    var date: LocalDate = documentDate

    @ColumnInfo("document_contractor_id")
    var contractor: Int = contractorId
    @Ignore
    private var entryList: MutableList<Entry> = mutableListOf()


    fun addEntry(entry: Entry) {
        entryList.add(entry)
    }

    fun getEntries(): MutableList<Entry> {
        return entryList
    }

    fun update(
        documentSymbol: String = "",
        documentDate: LocalDate = LocalDate.of(2012, 12, 22),
        contractorId: Int = 0,
    ) {
        symbol = documentSymbol
        date = documentDate
        contractor = contractorId
    }

    fun removeEntry(index: Int) {
        entryList.removeAt(index)
    }

    fun availableIndex(): Int {
        val index: Int = entryList.size
        entryList.add(index, Entry())
        return index
    }
}