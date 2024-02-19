package com.example.warehouseOrderApp.src.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contractors")
class Contractor(symbol: String = "", name: String = "") {

    @ColumnInfo("contractor_id")
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0

    @ColumnInfo("contractor_symbol")
    var symbol = symbol
        private set
    @ColumnInfo("contractor_name")
    var name = name
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