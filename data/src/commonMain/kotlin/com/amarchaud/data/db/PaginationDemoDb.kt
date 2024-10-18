package com.amarchaud.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.amarchaud.data.adapters.RoomConverter
import com.amarchaud.data.models.PageEntityModel
import com.amarchaud.data.models.UserEntityModel

@Database(
    entities = [
        UserEntityModel::class, PageEntityModel::class
    ], version = 1, exportSchema = false
)
@ConstructedBy(AppDatabaseConstructor::class)
@TypeConverters(
    RoomConverter.LocalDateRoomConverter::class,
)
abstract class PaginationDemoDb : RoomDatabase() {
    abstract fun getPaginationDemoDao(): PaginationDemoDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<PaginationDemoDb> {
    override fun initialize(): PaginationDemoDb
}
