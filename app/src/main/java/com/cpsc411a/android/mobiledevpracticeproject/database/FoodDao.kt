package com.cpsc411a.android.mobiledevpracticeproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cpsc411a.android.mobiledevpracticeproject.Food
import java.util.*

@Dao
interface FoodDao {

    @Query("SELECT * FROM food")
    fun getFoodList(): LiveData<List<Food>>

    @Query("SELECT * FROM food WHERE id=(:id)")
    fun getFood(id: UUID): LiveData<Food?>

    @Update
    fun updateFood(food: Food)

    @Insert
    fun addFood(food: Food)
}