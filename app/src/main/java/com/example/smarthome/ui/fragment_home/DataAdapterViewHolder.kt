package com.example.smarthome.ui.fragment_home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.ui.data.model.DataModel

class DataAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private fun bindLights(item: DataModel.Light) {
        val condition: ImageView? = itemView.findViewById(R.id.light_condition)
        val name: TextView? = itemView.findViewById(R.id.light_name)
        val place: TextView? = itemView.findViewById(R.id.light_place)

        if (item.condition) {
            condition?.setImageResource(R.drawable.light_on)
        } else {
            condition?.setImageResource(R.drawable.light_off)
        }

        name?.text = item.name

        place?.text = item.place
    }

    private fun bindCameras(item: DataModel.Camera) {
        val name: TextView? = itemView.findViewById(R.id.camera_name)
        val place: TextView? = itemView.findViewById(R.id.camera_place)

        name?.text = item.name
        place?.text = item.place

    }

    private fun bindDetectors(item: DataModel.Detector) {
        val name: TextView? = itemView.findViewById(R.id.detector_name)
        val place: TextView? = itemView.findViewById(R.id.detector_place)
        val statement: TextView? = itemView.findViewById(R.id.detector_statement)

        name?.text = item.name
        place?.text = item.place
        statement?.text = item.statement

    }

    fun bind(dataModel: DataModel) {
        when (dataModel) {
            is DataModel.Camera -> bindCameras(dataModel)
            is DataModel.Detector -> bindDetectors(dataModel)
            is DataModel.Light -> bindLights(dataModel)
        }
    }
}