package com.example.test.adapters

import android.util.*
import android.view.*
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.*
import com.example.test.*
import com.example.test.data.plant.*
import com.example.test.databinding.*

class AreaAdapter : PagingDataAdapter<PlantData, AreaAdapter.AreaViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        return AreaViewHolder(
            ItemPlantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class AreaViewHolder(
        private val binding: ItemPlantBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.plant?.let { item ->
                    Log.d("com.example", "name:" + item.F_Name_Ch)
                    navigateToDetail(item.F_Name_Ch, view)
                }
            }
        }

        var temp: PlantData? = null

        fun bind(item: PlantData?) {
            this.temp = item
            binding.apply {
                plant = item
                executePendingBindings()
            }
        }

        private fun navigateToDetail(plant: String, view: View) {
            val direction = AreaFragmentDirections
                .actionAreaToPlant(plant)
            view.findNavController().navigate(direction)
        }
    }
}

private class UserDiffCallback : DiffUtil.ItemCallback<PlantData>() {
    override fun areItemsTheSame(oldItem: PlantData, newItem: PlantData): Boolean {
        return oldItem.F_Name_Ch == newItem.F_Name_Ch
    }

    override fun areContentsTheSame(oldItem: PlantData, newItem: PlantData): Boolean {
        return oldItem == newItem
    }
}
