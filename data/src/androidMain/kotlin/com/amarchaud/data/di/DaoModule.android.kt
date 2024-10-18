package com.amarchaud.data.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.amarchaud.data.db.PaginationDemoDb
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val daoModule = module {
    single<RoomDatabase.Builder<PaginationDemoDb>> {
        val appContext = androidContext()
        val dbFile = appContext.getDatabasePath("PaginationDemoDb.db")
        Room.databaseBuilder<PaginationDemoDb>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}