package com.cpsc411a.android.mobiledevpracticeproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class FoodViewModel : ViewModel() {
    private val foodRepository = FoodRepository.get()
    private val foodIdLiveData = MutableLiveData<UUID>()
    var foodLiveData: LiveData<Food?> =
        Transformations.switchMap(foodIdLiveData) { foodId ->
            foodRepository.getFood(foodId)
        }
    fun loadFood(foodId: UUID) {
        foodIdLiveData.value = foodId
    }
    fun saveFood(food: Food) {
        foodRepository.updateFood(food)
    }
    fun addFood(food: Food) {
        foodRepository.addFood(food)
    }
}