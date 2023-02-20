package com.cpsc411a.android.mobiledevpracticeproject

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class FoodListViewModel : ViewModel() {
    // this grabs from the db
    private val foodRepository = FoodRepository.get()
    val foodListLiveData = foodRepository.getFoodList()
}