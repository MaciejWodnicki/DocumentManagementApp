package com.example.warehouseOrderApp.src.repositories

import com.example.warehouseOrderApp.src.data.Contractor

object ContractorService {
    private var contractorList: MutableList<Contractor> = mutableListOf()

    fun addContractor(contractor: Contractor?){
        if(contractor == null)
            return
        contractorList.add(contractor)
    }
    fun listOfContractors(): MutableList<Contractor> {
        return contractorList
    }

    fun data(index:Int): Contractor {
        return contractorList[index]
    }

}