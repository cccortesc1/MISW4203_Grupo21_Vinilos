package com.uniandes.miso.vinyls.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uniandes.miso.vinyls.database.dao.AlbumsDao
import com.uniandes.miso.vinyls.database.dao.ArtistsDao
import com.uniandes.miso.vinyls.database.dao.CollectorsDao
import com.uniandes.miso.vinyls.models.Album
import com.uniandes.miso.vinyls.models.Artist
import com.uniandes.miso.vinyls.models.Collector
import com.uniandes.miso.vinyls.utils.*


@Database(entities = [Collector::class], version = 1, exportSchema = false)
@TypeConverters(*[FavoritePerformersTypeConverter::class,CommentTypeConverter::class, CollectorAlbumsTypeConverter::class] )
abstract class VinylRoomDatabase : RoomDatabase() {
    //abstract fun albumsDao(): AlbumsDao
   // abstract fun artistsDao(): ArtistsDao
    abstract fun collectorsDao(): CollectorsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: VinylRoomDatabase? = null

        fun getDatabase(context: Context): VinylRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VinylRoomDatabase::class.java,
                    "vinyls_database",


                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}