package com.uniandes.miso.vinyls.di

import android.app.Application
import android.content.Context
import com.uniandes.miso.vinyls.database.dao.CollectorsDao
import com.uniandes.miso.vinyls.repositories.ArtistsRepository
import com.uniandes.miso.vinyls.repositories.AlbumsRepository
import com.uniandes.miso.vinyls.repositories.CollectorsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideCollectorRepository(application: Application, collectorsDao: CollectorsDao): CollectorsRepository =
        CollectorsRepository(application, collectorsDao = collectorsDao)

    @Provides
    fun provideArtistRepository(@ApplicationContext context: Context): ArtistsRepository = ArtistsRepository(context)

    @Provides
    fun provideAlbumRepository(@ApplicationContext context: Context): AlbumsRepository = AlbumsRepository(context)
}