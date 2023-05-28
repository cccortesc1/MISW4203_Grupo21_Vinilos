package com.uniandes.miso.vinyls.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uniandes.miso.vinyls.models.Collector

@Dao
interface CollectorsDao {

    @Query("SELECT * FROM collectors")
    fun getCollectors():List<Collector>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(collector: Collector)

    @Query("DELETE FROM collectors")
    suspend fun deleteAll()
}