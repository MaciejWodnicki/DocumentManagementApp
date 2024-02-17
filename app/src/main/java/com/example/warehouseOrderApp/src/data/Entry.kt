package com.example.warehouseOrderApp.src.data

data class Entry(var wareName: String, var wareUnit: Unit, var wareAmount: Float) {
    private var name: String = wareName
    private var amount: Float = wareAmount
    private var unit: Unit = wareUnit


}