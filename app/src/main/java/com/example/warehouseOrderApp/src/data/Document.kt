package com.example.warehouseOrderApp.src.data

import java.time.LocalDate

class Document (documentSymbol:String, documentDate: LocalDate, documentContractor: Contractor){
    private var symbol:String = documentSymbol
    private var date:LocalDate = documentDate
    private var contractor: Contractor = documentContractor
    private var entryList: MutableList<Entry> = mutableListOf()


    fun addEntry(entry: Entry){
        entryList.add(entry)
    }

}