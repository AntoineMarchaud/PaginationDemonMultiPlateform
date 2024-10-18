package com.amarchaud.data.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.amarchaud.data.db.PaginationDemoDao
import com.amarchaud.data.db.PaginationDemoDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

expect val daoModule : Module

val daoCommonModule = module {
    single<PaginationDemoDb> {
        val builder = get<RoomDatabase.Builder<PaginationDemoDb>>()
        builder
            //.addMigrations(MIGRATIONS)
            //.fallbackToDestructiveMigrationOnDowngrade()
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    single<PaginationDemoDao> {
        val db = get<PaginationDemoDb>()
        db.getPaginationDemoDao()
    }
}

