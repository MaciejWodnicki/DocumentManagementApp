package com.example.warehouseOrderApp.src.repositories

import com.example.warehouseOrderApp.MainActivity
import com.example.warehouseOrderApp.src.data.Document
import com.example.warehouseOrderApp.src.data.DocumentEntryCrossRef
import com.example.warehouseOrderApp.src.data.Entry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

object DocumentsService {
    var context: CoroutineContext? = null

    fun provideCoroutineContext(coroutineContext: CoroutineContext) {
        context = coroutineContext
    }

    fun addDocument(document: Document) {
        runBlocking {
            MainActivity.database.documentsDao().insert(document)
        }
    }

    fun listOfDocuments(): MutableList<Document> {
        val currentDocumentList: MutableList<Document>
        runBlocking(Dispatchers.IO) {
            currentDocumentList = MainActivity.database.documentsDao().getAllDocuments().first()
        }
        return currentDocumentList
    }

    fun get(index: Long): Document {
        var document: Document
        runBlocking{
            document = MainActivity.database.documentsDao().getDocument(index).first()
        }
        return document
    }

    fun updateDocument(document: Document) {
        CoroutineScope(context!!).async {
            MainActivity.database.documentsDao().update(document)
        }
    }

    fun addEntry(documentId: Long, entry: Entry) {
        CoroutineScope(context!!).async {
            MainActivity.database.entryDao().insert(entry)
            MainActivity.database.documentEntryCrossRefDao().insert(DocumentEntryCrossRef(entry.id,documentId))
        }
    }

    fun getDocumentEntries(documentId: Long): List<Entry> {
        var entries: List<Entry>
        runBlocking{
            entries = MainActivity.database.entryDao().getDocumentEntries(documentId).first()
        }
        return entries
    }

    fun getEntry(entryId:Long): Entry{
        var entry: Entry
        runBlocking{
            entry = MainActivity.database.entryDao().getEntry(entryId).first()
        }
        return entry
    }
    fun updateEntry(entry: Entry){
        CoroutineScope(context!!).async{
            MainActivity.database.entryDao().update(entry)
        }
    }

}