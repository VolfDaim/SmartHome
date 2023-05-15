package com.example.smarthome.ui.fragment_home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.marginBottom
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.smarthome.R
import com.example.smarthome.httpclient.ApiInterface
import com.example.smarthome.ui.data.model.DataModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type


class HomeFragmentDevices : Fragment() {

    private var swipeRefresh: SwipeRefreshLayout? = null
    private var createDevice: FloatingActionButton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home_devices, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<RecyclerView?>(R.id.rv_devices).apply {
            hasFixedSize()
            this.adapter = dataAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        swipeRefresh = view.findViewById(R.id.swipeRefresh)
        createDevice = view.findViewById(R.id.add_device)

    }

    @SuppressLint("ResourceType")
    override fun onStart() {
        super.onStart()

        swipeRefresh?.setOnRefreshListener {
            var lights = ArrayList<DataModel.Light>()
            var cameras = ArrayList<DataModel.Camera>()
            var detectors = ArrayList<DataModel.Detector>()

            val getLights = ApiInterface.create().getAllLights()
            val getCameras = ApiInterface.create().getAllCameras()
            val getDetectors = ApiInterface.create().getAllDetectors()

            val gson = Gson()
            var json_lights: String = gson.toJson(lights)
            val json_cameras: String = gson.toJson(cameras)
            val json_detectors: String = gson.toJson(detectors)
            val prefs = activity?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
            val editor = prefs?.edit()
            val devices = ArrayList<DataModel>()


            /**
             * Get all Lights
             */
            getLights.enqueue(object :
                Callback<ArrayList<DataModel.Light>> {
                override fun onResponse(
                    call: Call<ArrayList<DataModel.Light>>,
                    response: Response<ArrayList<DataModel.Light>>
                ) {
                    if (response?.body() != null) {
                        Log.i("INFO", "All data is update")
                        lights.addAll(response.body()!!)
                        json_lights = gson.toJson(lights)
                        editor?.putString("lights", json_lights)
                        devices.addAll(lights)
                        editor?.apply()
                        dataAdapter.setData(devices)

                    }
                }

                override fun onFailure(call: Call<ArrayList<DataModel.Light>>?, t: Throwable?) {
                    Toast.makeText(view?.context, "Response Failed", Toast.LENGTH_SHORT).show()
                }
            })

            /*
            /**
             * Get all Cameras
             */
            getCameras.enqueue(object :
                Callback<ArrayList<DataModel.Camera>> {
                override fun onResponse(
                    call: Call<ArrayList<DataModel.Camera>>,
                    response: Response<ArrayList<DataModel.Camera>>
                ) {
                    if (response?.body() != null) {
                        Log.i("INFO", "All data is update")
                        cameras.addAll(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<ArrayList<DataModel.Camera>>?, t: Throwable?) {
                    Toast.makeText(view?.context, "Response Failed", Toast.LENGTH_SHORT).show()
                }
            })

            /**
             * Get all Detectors
             */
            getDetectors.enqueue(object :
                Callback<ArrayList<DataModel.Detector>> {
                override fun onResponse(
                    call: Call<ArrayList<DataModel.Detector>>,
                    response: Response<ArrayList<DataModel.Detector>>
                ) {
                    if (response?.body() != null) {
                        Log.i("INFO", "All data is update")
                        detectors.addAll(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<ArrayList<DataModel.Detector>>?, t: Throwable?) {
                    Toast.makeText(view?.context, "Response Failed", Toast.LENGTH_SHORT).show()
                }
            })
            */


            editor?.putString("cameras", json_cameras)
            editor?.putString("detectors", json_detectors)

            devices.addAll(cameras)
            devices.addAll(detectors)
            swipeRefresh?.isRefreshing = false
        }

        createDevice?.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_home_frame, CreateDevice.newInstance())
                commit()
            }
        }
        val gson = Gson()
        val devices = ArrayList<DataModel>()
        val sharedPrefs = activity?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)

        val json_lights = sharedPrefs?.getString("lights", null)
        val json_cameras = sharedPrefs?.getString("cameras", null)
        val json_detectors = sharedPrefs?.getString("detectors", null)

        val listTypeLights: Type = object : TypeToken<ArrayList<DataModel.Light?>?>() {}.type
        val listTypeCameras: Type = object : TypeToken<ArrayList<DataModel.Camera?>?>() {}.type
        val listTypeDetectors: Type = object : TypeToken<ArrayList<DataModel.Detector?>?>() {}.type

        if (json_lights != null)
            devices.addAll(gson.fromJson(json_lights, listTypeLights))
        if (json_cameras != null)
            devices.addAll(gson.fromJson(json_cameras, listTypeCameras))
        if (json_detectors != null)
            devices.addAll(gson.fromJson(json_detectors, listTypeDetectors))

        if (devices != null)
            dataAdapter.setData(devices)
    }

    private fun doOnClick(light: DataModel.Light) {

    }

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(light: DataModel.Light) {
            TODO("Not yet implemented")
        }

    }

    private val dataAdapter: HomeDeviceAdapter by lazy {
        HomeDeviceAdapter()
    }

    companion object {
        fun newInstance() = HomeFragmentDevices()
    }

    private fun getMockData(): ArrayList<DataModel> = arrayListOf(
        DataModel.Light(
            name = "Light 1",
            place = "Place 1",
            condition = true
        ),
        DataModel.Light(
            name = "Light 2",
            place = "Place 2",
            condition = false
        ),

        )

}