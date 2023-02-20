package com.cpsc411a.android.mobiledevpracticeproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

private const val TAG = "FoodListFragment"

class FoodListFragment : Fragment()
{
    interface Callbacks {
        fun onFoodSelected(foodId: UUID)
    }
    private var callbacks: Callbacks? = null

    private lateinit var foodListRecyclerView: RecyclerView
    private var adapter: FoodAdapter? = FoodAdapter(emptyList())
    private val foodListViewModel: FoodListViewModel by lazy {
        ViewModelProviders.of(this)[FoodListViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    private inner class FoodHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var food: Food

        private val nameTextView: TextView = this.itemView.findViewById(R.id.list_item_food_name)
        private val caloriesTextView: TextView = this.itemView.findViewById(R.id.list_item_food_calories)
        private val typeTextView: TextView = this.itemView.findViewById(R.id.list_item_food_type)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(food: Food) {
            this.food = food
            nameTextView.text = food.name
            caloriesTextView.text = food.calories.toString()
            typeTextView.text = food.type
        }

        override fun onClick(v: View) {
            Log.d(TAG, "${food.name} was pressed")
//            Toast.makeText(context, "${crime.title} clicked!", Toast.LENGTH_SHORT)
//                .show()
            callbacks?.onFoodSelected(food.id)
        }
    }

    private inner class FoodAdapter (var foodList: List<Food>)
        : RecyclerView.Adapter<FoodHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
            val view = layoutInflater.inflate(R.layout.list_item_food, parent, false)
            return FoodHolder(view)
        }

        override fun getItemCount() = foodList.size

        override fun onBindViewHolder(holder: FoodHolder, position: Int) {
            val food = this.foodList[position]
            holder.bind(food)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food_list, container, false)
        this.foodListRecyclerView = view.findViewById(R.id.food_list_recycler_view) as RecyclerView
        this.foodListRecyclerView.layoutManager = LinearLayoutManager(context)
        foodListRecyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodListViewModel.foodListLiveData.observe(
            viewLifecycleOwner,
            Observer { foodList ->
                foodList?.let {
                    Log.i(TAG, "There are ${foodList.size} items in the food list.")
                    updateUI(foodList)
                }
            })
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun updateUI(foodList: List<Food>) {
        adapter = FoodAdapter(foodList)
        this.foodListRecyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(): FoodListFragment {
            return FoodListFragment()
        }
    }
}