package com.uniandes.miso.vinyls.di

import android.content.Context
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
    fun provideCollectorRepository(@ApplicationContext context: Context): CollectorsRepository = CollectorsRepository(context)

    @Provides
    fun provideAlbumRepository(@ApplicationContext context: Context): AlbumsRepository = AlbumsRepository(context)
}