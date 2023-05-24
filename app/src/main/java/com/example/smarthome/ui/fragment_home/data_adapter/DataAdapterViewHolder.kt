package com.example.smarthome.ui.fragment_home.data_adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.httpclient.ApiInterface
import com.example.smarthome.httpclient.ApiService
import com.example.smarthome.ui.fragment_home.bo.DataModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class DataAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private fun bindLights(item: DataModel.Light) {
        val sharedPrefs = itemView.context.getSharedPreferences(
            "shared preferences",
            Context.MODE_PRIVATE
        )
        val token = sharedPrefs?.getString("token", null)

        var condition: ImageView? = itemView.findViewById(R.id.light_condition)
        val name: TextView? = itemView.findViewById(R.id.light_name)
        val place: TextView? = itemView.findViewById(R.id.light_place)
        val button: Button? = itemView.findViewById(R.id.switch_light)
        val deleteButton: Button? = itemView.findViewById(R.id.delete_light)

        if (item.condition) {
            condition?.setImageResource(R.drawable.light_on)
        } else {
            condition?.setImageResource(R.drawable.light_off)
        }

        name?.text = item.name

        place?.text = item.place

        deleteButton?.setOnClickListener {
            ApiService().deleteLight(itemView.context, item.id!!)
        }

        button?.setOnClickListener {
            val toggleLight = ApiInterface.create().toggleLight(token, item.id)
            Log.i("INFO", "BUTTON PRESSED")

            toggleLight.enqueue(object :
                Callback<DataModel.Light> {
                override fun onResponse(
                    call: Call<DataModel.Light>,
                    response: Response<DataModel.Light>
                ) {
                    if (response.body() != null) {
                        val lights = ArrayList<DataModel.Light>()
                        val gson = Gson()
                        var json_lights = sharedPrefs.getString("lights", null)
                        val listTypeLights: Type =
                            object : TypeToken<ArrayList<DataModel.Light?>?>() {}.type
                        lights.addAll(gson.fromJson(json_lights, listTypeLights))

                        lights.get(adapterPosition).condition =
                            !lights.get(adapterPosition).condition
                        json_lights = gson.toJson(lights)
                        val editor = sharedPrefs.edit()
                        editor.putString("lights", json_lights)
                        editor.apply()

                        item.condition = !item.condition
                        if (item.condition) {
                            condition?.setImageResource(R.drawable.light_on)
                        } else {
                            condition?.setImageResource(R.drawable.light_off)
                        }
                    }
                }

                override fun onFailure(call: Call<DataModel.Light>, t: Throwable) {
                    Toast.makeText(itemView.context, "Response Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun bindCameras(item: DataModel.Camera) {
        val name: TextView? = itemView.findViewById(R.id.camera_name)
        val place: TextView? = itemView.findViewById(R.id.camera_place)
        val deleteButton: Button? = itemView.findViewById(R.id.delete_camera)
        deleteButton?.setOnClickListener {
            ApiService().deleteCamera(itemView.context, item.id!!)

        }
        name?.text = item.name
        place?.text = item.place

    }

    private fun bindDetectors(item: DataModel.Detector) {
        val name: TextView? = itemView.findViewById(R.id.detector_name)
        val place: TextView? = itemView.findViewById(R.id.detector_place)
        val statement: TextView? = itemView.findViewById(R.id.detector_statement)
        val deleteButton: Button? = itemView.findViewById(R.id.delete_detector)

        deleteButton?.setOnClickListener {
            ApiService().deleteDetector(itemView.context, item.id!!)
        }

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