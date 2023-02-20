package com.cpsc411a.android.mobiledevpracticeproject.database

import androidx.room.TypeConverter
import java.util.*

class FoodTypeConverters {
    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }
}