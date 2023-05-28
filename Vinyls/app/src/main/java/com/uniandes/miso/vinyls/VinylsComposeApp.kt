package com.uniandes.miso.vinyls

import android.app.Application
import com.uniandes.miso.vinyls.database.VinylRoomDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VinylsComposeApp: Application() {
    val database by lazy { VinylRoomDatabase.getDatabase(this) }
}