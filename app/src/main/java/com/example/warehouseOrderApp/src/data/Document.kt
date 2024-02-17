package com.example.warehouseOrderApp.src.data

import java.time.LocalDate

class Document(
    documentSymbol: String = "",
    documentDate: LocalDate = LocalDate.of(2012, 12, 22),
    contractorId: Int = -1,
) {
    var symbol: String = documentSymbol
        private set
    var date: LocalDate = documentDate
        private set
    var contractor: Int = contractorId
        private set
    private var entryList: MutableList<Entry> = mutableListOf()
        private set

    fun addEntry(entry: Entry) {
        entryList.add(entry)
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
}