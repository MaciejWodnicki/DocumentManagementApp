package com.example.warehouseOrderApp.src.data

class Contractor(symbol: String = "", name: String = "") {

    var name = name
        private set
    var symbol = symbol
        private set


    fun updateContractor(contractorSymbol: String = symbol, contractorName: String = name) {
        symbol = contractorSymbol
        name = contractorName
    }

    override
    fun toString(): String {
        return "Name: $name, Symbol: $symbol"
    }

}