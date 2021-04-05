package com.example.test.adapters

import android.util.*
import android.view.*
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.*
import com.example.test.*
import com.example.test.data.area.*
import com.example.test.databinding.*

class DisplayAreasAdapter : PagingDataAdapter<AreaData, DisplayAreasAdapter.DisplayAreasViewHolder>(DisplayAreasDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayAreasViewHolder {
        return DisplayAreasViewHolder(
            ItemAreaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DisplayAreasViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DisplayAreasViewHolder(
        private val binding: ItemAreaBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.area?.let { item ->
                    Log.d("com.example", "name:" + item.E_Name)
                    navigateToArea(ParcelableAreaData(item.E_Name,item.E_Pic_URL,item.E_Info), view)
                }
            }
        }

        var temp: AreaData? = null

        fun bind(item: AreaData?) {
            this.temp = item
            binding.apply {
                area = item
                executePendingBindings()
            }
        }

        private fun navigateToArea(area: ParcelableAreaData, view: View) {
            val direction = DisplayAreasFragmentDirections
                .actionDisplayAreasToArea(area)
            view.findNavController().navigate(direction)
        }
    }
}

private class DisplayAreasDiffCallback : DiffUtil.ItemCallback<AreaData>() {
    override fun areItemsTheSame(oldItem: AreaData, newItem: AreaData): Boolean {
        return oldItem.E_Name == newItem.E_Name
    }

    override fun areContentsTheSame(oldItem: AreaData, newItem: AreaData): Boolean {
        return oldItem == newItem
    }
}
