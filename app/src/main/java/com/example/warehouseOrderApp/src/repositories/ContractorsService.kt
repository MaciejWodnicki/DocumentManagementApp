package com.example.warehouseOrderApp.src.repositories

import com.example.warehouseOrderApp.src.data.Contractor

object ContractorsService {
    private var contractorList: MutableList<Contractor> = mutableListOf()

    fun addContractor(contractor: Contractor?) {
        if (contractor == null)
            return
        contractorList.add(contractor)
    }

    fun listOfContractors(): MutableList<Contractor> {
        return contractorList
    }

    fun get(index: Int): Contractor {
        return contractorList[index]
    }

    fun availableIndex(): Int {
        val index: Int = contractorList.size
        contractorList.add(index, Contractor())
        return index
    }

    fun removeContractor(index: Int) {
        contractorList.removeAt(index)
    }

}