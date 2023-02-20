package com.cpsc411a.android.mobiledevpracticeproject

import MainViewModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cpsc411a.android.mobiledevpracticeproject.motivation.MotivationActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


// log tags
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var motivationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val provider: ViewModelProvider = ViewModelProviders.of(this)
        val mainViewModel = provider.get(MainViewModel::class.java)
        Log.d(TAG, "Got a MainViewModel: $mainViewModel")

        // link motivation button to motivation page
        motivationButton = findViewById(R.id.motivation_button)
        motivationButton.setOnClickListener {
            val intent = Intent(this, MotivationActivity::class.java)
            startActivity(intent)
        }

        // Bottom Navigation Bar Stuff
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.home

        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.calendar_planner -> {
                    startActivity(Intent(applicationContext, CalendarActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                R.id.home -> return@setOnItemSelectedListener true
                R.id.food_list -> {
                    startActivity(Intent(applicationContext, FoodActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }


    // log functions
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