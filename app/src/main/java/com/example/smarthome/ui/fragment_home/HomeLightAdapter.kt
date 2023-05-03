package com.example.smarthome.ui.fragment_home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.ui.data.listClasses.Camera
import com.example.smarthome.ui.data.listClasses.Detector
import com.example.smarthome.ui.data.listClasses.Light

class HomeLightAdapter : RecyclerView.Adapter<EmptyViewHolder>() {

    private var lights = listOf<Light>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyViewHolder {
        return LightViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_lights_empty, parent, false)
        )

    }

    override fun onBindViewHolder(holder: EmptyViewHolder, position: Int) {
        when (holder) {
            is LightViewHolder -> {

                holder.onBind(lights[position])
            }
        }
    }

    override fun getItemCount(): Int = lights.size

    fun bindLights(newLights: List<Light>) {
        lights = newLights
        notifyDataSetChanged()
    }
}

abstract class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class LightViewHolder(itemView: View) : EmptyViewHolder(itemView) {
    private val condition: ImageView? = itemView.findViewById(R.id.light_condition)
    private val name: TextView? = itemView.findViewById(R.id.light_name)
    private val place: TextView? = itemView.findViewById(R.id.light_place)

    fun onBind(light: Light) {
        if (light.condition) {

            condition?.setImageResource(R.drawable.light_1)
        } else {

            condition?.setImageResource(R.drawable.light_0)
        }

        name?.text = light.name

        place?.text = light.place
    }
}

private class CameraViewHolder(itemView: View) : EmptyViewHolder(itemView) {
    private val name: TextView? = itemView.findViewById(R.id.camera_name)
    private val place: TextView? = itemView.findViewById(R.id.camera_place)

    fun onBind(camera: Camera) {


        name?.text = camera.name

        place?.text = camera.place
    }
}

private class DetectorViewHolder(itemView: View) : EmptyViewHolder(itemView) {
    private val name: TextView? = itemView.findViewById(R.id.light_name)
    private val place: TextView? = itemView.findViewById(R.id.light_place)

    fun onBind(detector: Detector) {

        name?.text = detector.name

        place?.text = detector.place
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface OnRecyclerItemClicked {
    fun onClick(light: Light)
}