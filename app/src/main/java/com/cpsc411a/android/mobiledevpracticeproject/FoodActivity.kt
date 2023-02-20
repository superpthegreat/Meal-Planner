package com.cpsc411a.android.mobiledevpracticeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

private const val TAG = "FoodActivity"

class FoodActivity : AppCompatActivity(),
    FoodListFragment.Callbacks, FoodFragment.Callbacks {
    private lateinit var createButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_food)

        val currentFragment = this.supportFragmentManager.findFragmentById(R.id.food_fragment_container)
        if (currentFragment == null) {
            val fragment = FoodListFragment.newInstance()
            this.supportFragmentManager
                .beginTransaction()
                .add(R.id.food_fragment_container, fragment)
                .commit()
        }

        this.createButton = this.findViewById(R.id.create_button)
        this.createButton.setOnClickListener {
            Log.d(TAG, "Create Food Button Clicked")
            val fragment = FoodFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.food_fragment_container, fragment)
                .addToBackStack("FoodFragment")
                .commit()
            createButton.visibility = View.GONE
        }

        // Bottom Navigation Bar Stuff
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.food_list

        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.calendar_planner -> {
                    startActivity(Intent(applicationContext, CalendarActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                R.id.home -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                R.id.food_list -> return@setOnItemSelectedListener true
            }
            false
        }
    }

    override fun onFoodSelected(foodId: UUID) {
        Log.d(TAG, "FoodActivity.onFoodSelected: $foodId")
        val fragment = FoodFragment.newInstance(foodId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.food_fragment_container, fragment)
            .addToBackStack("FoodFragment")
            .commit()
    }

    override fun onCancelPressed() {
        Log.d(TAG, "Cancel Button Pressed")
        createButton.visibility = View.VISIBLE
        this.supportFragmentManager.popBackStack()
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