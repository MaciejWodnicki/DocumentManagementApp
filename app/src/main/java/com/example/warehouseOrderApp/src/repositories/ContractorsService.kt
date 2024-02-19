package com.example.warehouseOrderApp.src.repositories

import android.content.Context
import com.example.warehouseOrderApp.MainActivity
import com.example.warehouseOrderApp.src.data.Contractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

object ContractorsService {
    private var contractorList: MutableList<Contractor> = mutableListOf()
    var context: CoroutineContext? = null

    fun provideCoroutineContext(coroutineContext: CoroutineContext){
        context = coroutineContext
    }
    fun addContractor(contractor: Contractor?) {
        if (contractor == null)
            return
        contractorList.add(contractor)
    }

    fun listOfContractors(): Flow<MutableList<Contractor>> {
        return MainActivity.database.contractorDao().getAllContractors()

    }

    fun get(index: Int): Contractor {
        return contractorList[index]
    }

    fun availableIndex(context: CoroutineContext): Int {
        val index: Int = contractorList.size
         CoroutineScope(context).async{
            MainActivity.database.contractorDao().insert(Contractor())
        }
        contractorList.add(index, Contractor())
        return index
    }

    fun removeContractor(index: Int) {
        contractorList.removeAt(index)
    }

}