package com.example.warehouseOrderApp.src.data

class Contractor(symbol: String, name:String) {

    private var name = name
        get(){
            return field
        }
    private var symbol = symbol
        get() = field

    fun updateContractor(contractorSymbol: String = symbol, contractorName:String = name) {
        symbol = contractorSymbol
        name = contractorName
    }


}