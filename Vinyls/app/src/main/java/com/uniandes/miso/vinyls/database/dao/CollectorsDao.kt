package com.uniandes.miso.vinyls.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uniandes.miso.vinyls.models.Collector

@Dao
interface CollectorsDao {
    @Query("DELETE FROM collectors_table")
    suspend fun deleteAll()

 @Insert(onConflict = OnConflictStrategy.REPLACE)
 suspend fun insertCollectors(collectors: List<Collector>)

 @Query("SELECT * FROM collectors_table ORDER BY id ASC")
 fun readCollectors(): List<Collector>
}