package com.cpsc411a.android.mobiledevpracticeproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import java.util.*

private const val TAG = "FoodFragmnent"
private const val ARG_FOOD_ID = "food_id"

class FoodFragment : Fragment() {

    interface Callbacks {
        fun onCancelPressed()
    }
    private var callbacks: Callbacks? = null

    private lateinit var food: Food

    private lateinit var nameField: EditText
    private lateinit var ingredientsField: EditText
    private lateinit var recipeField: EditText
    private lateinit var caloriesField: EditText
    private lateinit var typeField: EditText

    private lateinit var cancelButton: Button
    private lateinit var saveButton: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as FoodFragment.Callbacks?
    }

    private val foodViewModel : FoodViewModel by lazy {
        ViewModelProviders.of(this)[FoodViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        food = Food()

        if (arguments != null) {
            val foodId: UUID = arguments?.getSerializable(ARG_FOOD_ID) as UUID
            Log.d(TAG, "args bundle food ID: $foodId")
            // load from database
            foodViewModel.loadFood(foodId)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food, container, false)

        nameField = view.findViewById(R.id.food_name) as EditText
        ingredientsField = view.findViewById(R.id.food_ingredients) as EditText
        recipeField = view.findViewById(R.id.food_recipe) as EditText
        caloriesField = view.findViewById(R.id.food_calories) as EditText
        typeField = view.findViewById(R.id.food_type) as EditText

        cancelButton = view.findViewById(R.id.cancel_button)
        cancelButton.setOnClickListener {
            callbacks?.onCancelPressed()
        }

        saveButton = view.findViewById(R.id.save_button)
        saveButton.setOnClickListener {
            val newFood = Food()
            newFood.name = nameField.text.toString()
            newFood.ingredients = ingredientsField.text.toString()
            newFood.recipe = recipeField.text.toString()
            newFood.calories = caloriesField.text.toString().toIntOrNull() ?: 0
            newFood.type = typeField.text.toString()
            foodViewModel.addFood(newFood)

            // then go back to list
            activity?.supportFragmentManager?.popBackStack()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodViewModel.foodLiveData.observe(
            viewLifecycleOwner,
            Observer { food ->
                food?.let {
                    this.food = food
                    updateUI()
                }
            })
    }

    companion object {
        fun newInstance(): FoodFragment {
            return FoodFragment()
        }

        fun newInstance(foodId: UUID): FoodFragment {
            val args = Bundle().apply {
                putSerializable(ARG_FOOD_ID, foodId)
            }
            return FoodFragment().apply {
                arguments = args
            }
        }
    }

    override fun onStop() {
        super.onStop()
        foodViewModel.saveFood(food)
    }

    override fun onDestroy() {
        super.onDestroy()
        //callbacks?.onCancelPressed()
    }

    private fun updateUI() {
        nameField.setText(food.name.toString())
        ingredientsField.setText(food.ingredients.toString())
        recipeField.setText(food.recipe.toString())
        caloriesField.setText(food.calories.toString())
        typeField.setText(food.type.toString())
    }
}