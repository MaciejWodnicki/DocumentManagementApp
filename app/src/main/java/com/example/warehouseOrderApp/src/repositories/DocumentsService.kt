package com.example.warehouseOrderApp.src.repositories

import com.example.warehouseOrderApp.src.data.Document
import java.time.LocalDate

object DocumentsService {
    private var documentList: MutableList<Document> = mutableListOf()

    fun addDocument(symbol:String, date: LocalDate, contractorId: Int){
        var newDocument: Document = Document(symbol, date, contractorId)

        if (!documentList.contains(newDocument)){
            documentList.add(newDocument)
        }
    }
    fun listOfDocuments(): MutableList<Document> {
        return documentList
    }

    fun get(index:Int): Document {
        return documentList[index]
    }

    fun availableIndex(): Int{
        val index:Int = documentList.size
        documentList.add(index, Document())
        return index
    }

}