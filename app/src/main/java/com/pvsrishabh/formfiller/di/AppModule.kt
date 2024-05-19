package com.pvsrishabh.formfiller.di

import android.app.Application
import com.pvsrishabh.formfiller.data.manager.LocalUserManagerImpl
import com.pvsrishabh.formfiller.domain.manager.LocalUserManager
import com.pvsrishabh.formfiller.domain.usecases.app_entry.AppEntryUseCases
import com.pvsrishabh.formfiller.domain.usecases.app_entry.ReadAppEntry
import com.pvsrishabh.formfiller.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}