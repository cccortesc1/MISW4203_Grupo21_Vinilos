package com.uniandes.miso.vinyls.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uniandes.miso.vinyls.models.Artist

@Dao
interface ArtistsDao {

    @Query("SELECT * FROM artists")
    fun getArtists():List<Artist>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(artist: Artist)

    @Query("DELETE FROM artists")
    suspend fun deleteAll()
}