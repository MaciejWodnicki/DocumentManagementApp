package com.example.warehouseOrderApp.src.repositories

import android.content.Context
import com.example.warehouseOrderApp.MainActivity
import com.example.warehouseOrderApp.src.data.Contractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

object ContractorsService {
    var context: CoroutineContext? = null

    fun provideCoroutineContext(coroutineContext: CoroutineContext) {
        context = coroutineContext
    }

    fun addContractor(contractor: Contractor) {
        runBlocking {
            MainActivity.database.contractorDao().insert(contractor)
        }
    }

    fun listOfContractors(): MutableList<Contractor> {
        val currentContractorList: MutableList<Contractor>
        runBlocking(Dispatchers.IO) {
            currentContractorList = MainActivity.database.contractorDao().getAllContractors().first()
        }
        return currentContractorList
    }

    fun get(index: Long): Contractor {
        var contractor: Contractor
        runBlocking{
            contractor = MainActivity.database.contractorDao().getContractor(index).first()
        }
        return contractor
    }


    fun removeContractor(contractor: Contractor) {
        CoroutineScope(context!!).async {
            MainActivity.database.contractorDao().delete(contractor)
        }
    }

    fun updateContractor(contractor: Contractor) {
        CoroutineScope(context!!).async {
            MainActivity.database.contractorDao().update(contractor)
        }
    }

}