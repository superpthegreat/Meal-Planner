package com.cpsc411a.android.mobiledevpracticeproject

import CalendarViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG = "CalendarActivity"

class CalendarActivity : AppCompatActivity() {

    private lateinit var foodButton: Button
    private lateinit var homeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_calendar)

        val provider: ViewModelProvider = ViewModelProviders.of(this)
        val calendarViewModel = provider.get(CalendarViewModel::class.java)
        Log.d(TAG, "Got a CalendarViewModel: $calendarViewModel")

        // Bottom Navigation Bar Stuff
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.calendar_planner

        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.calendar_planner -> return@setOnItemSelectedListener true
                R.id.home -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                R.id.food_list -> {
                    startActivity(Intent(applicationContext, FoodActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
    
    //log functions
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

}