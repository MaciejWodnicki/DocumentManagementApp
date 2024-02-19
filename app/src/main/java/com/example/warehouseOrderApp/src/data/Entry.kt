package com.example.warehouseOrderApp.src.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String = "",
    var unit: UnitOfMeasure = UnitOfMeasure.U,
    var amount: Float = 0.0f)