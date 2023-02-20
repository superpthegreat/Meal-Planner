package com.cpsc411a.android.mobiledevpracticeproject

import android.app.Application

class MobileDevPracticeProjectApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FoodRepository.initialize(this)
    }
}