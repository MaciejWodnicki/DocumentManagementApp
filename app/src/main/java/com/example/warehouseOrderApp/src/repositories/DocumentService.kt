package com.example.warehouseOrderApp.src.repositories

import com.example.warehouseOrderApp.src.data.Contractor
import com.example.warehouseOrderApp.src.data.Document
import java.time.LocalDate

object DocumentService {
    private var documentList: MutableList<Document> = mutableListOf()

    fun addDocument(symbol:String, date: LocalDate, contractor: Contractor){
        var newDocument: Document = Document(symbol, date, contractor)

        if (!documentList.contains(newDocument)){
            documentList.add(newDocument)
        }
    }
    fun listOfDocuments(): MutableList<Document> {
        return documentList
    }

    fun data(index:Int): Document {
        return documentList[index]
    }

}