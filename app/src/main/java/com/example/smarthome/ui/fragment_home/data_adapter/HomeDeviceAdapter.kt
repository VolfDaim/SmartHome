package com.example.smarthome.ui.fragment_home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.ui.fragment_home.bo.DataModel
import com.example.smarthome.ui.fragment_home.data_adapter.DataAdapterViewHolder

class HomeDeviceAdapter : RecyclerView.Adapter<DataAdapterViewHolder>() {

    private val adapterData = mutableListOf<DataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapterViewHolder {

        val layout = when (viewType) {
            TYPE_LIGHT -> R.layout.item_light
            TYPE_CAMERA -> R.layout.item_camera
            TYPE_DETECTOR -> R.layout.item_detector
            else -> throw IllegalArgumentException("Invalid Type")
        }

        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)

        return DataAdapterViewHolder(view)

    }

    override fun onBindViewHolder(holder: DataAdapterViewHolder, position: Int) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount(): Int = adapterData.size
    
    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]){
            is DataModel.Light -> TYPE_LIGHT
            is DataModel.Camera -> TYPE_CAMERA
            is DataModel.Detector -> TYPE_DETECTOR
        }
    }

    fun setData(data: List<DataModel>){
        adapterData.apply {
            clear()
            addAll(data)
            notifyDataSetChanged()
        }
    }

    fun deleteItem(index: Int){
        adapterData.removeAt(index)
        notifyDataSetChanged()
    }

    companion object {
        private const val TYPE_LIGHT = 0
        private const val TYPE_CAMERA = 1
        private const val TYPE_DETECTOR = 2
    }
}


private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface OnRecyclerItemClicked {
    fun onClick(light: DataModel.Light)
}