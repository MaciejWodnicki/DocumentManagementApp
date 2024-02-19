package com.example.warehouseOrderApp.src.repositories

import com.example.warehouseOrderApp.src.data.Document
import java.time.LocalDate

object DocumentsService {
    private var documentList: MutableList<Document> = mutableListOf()

    fun addDocument(symbol: String, date: LocalDate, contractorId: Long) {
        var newDocument: Document = Document(symbol, date, contractorId)

        if (!documentList.contains(newDocument)) {
            documentList.add(newDocument)
        }
    }

    fun listOfDocuments(): MutableList<Document> {
        return documentList
    }

    fun get(index: Long): Document {
        return documentList[index.toInt()]
    }

    fun availableIndex(): Int {
        val index: Int = documentList.size
        documentList.add(index, Document(contractorId = 0))
        return index
    }

    fun removeDocument(index: Long) {
        documentList.removeAt(index.toInt())
    }

}