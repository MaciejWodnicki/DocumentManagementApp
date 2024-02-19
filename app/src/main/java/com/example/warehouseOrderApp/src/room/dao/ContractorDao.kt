package com.example.warehouseOrderApp.src.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.warehouseOrderApp.src.data.Contractor
import kotlinx.coroutines.flow.Flow

@Dao
interface ContractorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contractor: Contractor)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(contractor: Contractor)

    @Delete
    suspend fun delete(contractor: Contractor)

    @Query("Select * FROM contractors")
    fun getAllContractors(): Flow<MutableList<Contractor>>

    @Query("Select * FROM contractors WHERE contractor_id =:contractorId")
    fun getContractor(contractorId:Long): Flow<Contractor>

    @Query("DELETE FROM Contractors")
    fun ClearTable()
}