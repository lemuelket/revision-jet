package com.godsonpeya.family.di

import android.app.Application
import androidx.room.Room
import com.godsonpeya.family.data.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(app: Application): AppDataBase{
        return Room.databaseBuilder(app, AppDataBase::class.java,"family_db")
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(db:AppDataBase) = db.memberDao()

}