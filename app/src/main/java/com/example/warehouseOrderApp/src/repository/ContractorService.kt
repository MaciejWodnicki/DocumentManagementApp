package com.example.warehouseOrderApp.src.repository

import com.example.warehouseOrderApp.src.data.Contractor

object ContractorService {
    private var contractorList: MutableList<Contractor> = mutableListOf()

    fun addContractor(symbol:String, name:String){
        var newContractor:Contractor = Contractor(symbol,name)

        if (!contractorList.contains(newContractor)){
            contractorList.add(newContractor)
        }
    }
    fun listOfContractors(): MutableList<Contractor> {
        return contractorList
    }

    fun data(index:Int): Contractor {
        return contractorList[index]
    }

}