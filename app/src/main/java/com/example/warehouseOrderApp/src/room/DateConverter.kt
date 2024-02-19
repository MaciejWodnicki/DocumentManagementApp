package com.example.warehouseOrderApp.src.room

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset

open class DateConverter {
    @TypeConverter
    fun toDate(date: Long?): LocalDate?{
        return date?.let { Instant.ofEpochSecond(date).atZone(ZoneId.systemDefault()).toLocalDate(); }
    }

    @TypeConverter
    fun fromDate(date: LocalDate?): Long?{

        return date?.atStartOfDay(ZoneId.systemDefault())?.toEpochSecond()
    }
}