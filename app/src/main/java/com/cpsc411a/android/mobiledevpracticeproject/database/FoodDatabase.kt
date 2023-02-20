package com.cpsc411a.android.mobiledevpracticeproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cpsc411a.android.mobiledevpracticeproject.Food

@Database(entities = [ Food::class ], version=1)
@TypeConverters(FoodTypeConverters::class)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}