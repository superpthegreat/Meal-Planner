package com.cpsc411a.android.mobiledevpracticeproject

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Food(
    @PrimaryKey var id: UUID = UUID.randomUUID(),
    var name: String = "",
    var ingredients: String = "",
    var recipe: String = "",
    var calories: Int = 0,
    var type: String = ""
)