package com.example.warehouseOrderApp.src.data

data class Entry(
    var name: String = "",
    var unit: UnitOfMeasure = UnitOfMeasure.U,
    var amount: Float = 0.0f)