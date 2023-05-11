package com.example.smarthome.ui.fragment_home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.smarthome.R
import com.example.smarthome.httpclient.ApiInterface
import com.example.smarthome.ui.data.model.DataModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
        }

        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        createDevice = view.findViewById(R.id.add_device)

    }

    @SuppressLint("ResourceType")
    override fun onStart() {
        super.onStart()

        swipeRefresh?.setOnRefreshListener {
            var devices = ArrayList<DataModel.Light>()
            val getLights = ApiInterface.create().getAllLights()
            val getCameras = ApiInterface.create().getAllCameras()
            val getDetectors = ApiInterface.create().getAllDetectors()

            /*
            /**
             * Get all Lights
             */
            getLights.enqueue(object :
                Callback<List<DataModel.Light>> {
                override fun onResponse(
                    call: Call<List<DataModel.Light>>,
                    response: Response<List<DataModel.Light>>
                ) {
                    if (response?.body() != null) {
                        Log.i("INFO", "All data is update")
                        devices.addAll(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<List<DataModel.Light>>?, t: Throwable?) {
                    Toast.makeText(view?.context, "Response Failed", Toast.LENGTH_SHORT).show()
                }
            })

            /**
             * Get all Cameras
             */
            getCameras.enqueue(object :
                Callback<List<DataModel.Camera>> {
                override fun onResponse(
                    call: Call<List<DataModel.Camera>>,
                    response: Response<List<DataModel.Camera>>
                ) {
                    if (response?.body() != null) {
                        Log.i("INFO", "All data is update")
                        devices.addAll(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<List<DataModel.Camera>>?, t: Throwable?) {
                    Toast.makeText(view?.context, "Response Failed", Toast.LENGTH_SHORT).show()
                }
            })

            /**
             * Get all Detectors
             */
            getDetectors.enqueue(object :
                Callback<List<DataModel.Detector>> {
                override fun onResponse(
                    call: Call<List<DataModel.Detector>>,
                    response: Response<List<DataModel.Detector>>
                ) {
                    if (response?.body() != null) {
                        Log.i("INFO", "All data is update")
                        devices.addAll(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<List<DataModel.Detector>>?, t: Throwable?) {
                    Toast.makeText(view?.context, "Response Failed", Toast.LENGTH_SHORT).show()
                }
            })
            */
            devices = getMockData()
            val gson = Gson()
            val json: String = gson.toJson(devices)
            val prefs = activity?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
            val editor = prefs?.edit()
            editor?.putString("lights", json)
            editor?.apply()

            dataAdapter.setData(devices)
            swipeRefresh?.isRefreshing = false
        }

        createDevice?.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_home_frame, CreateDevice.newInstance())
                commit()
            }
        }
        val gson = Gson()
        val sharedPrefs = activity?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val json = sharedPrefs?.getString("lights", null)
        val listType: Type = object : TypeToken<ArrayList<DataModel.Light?>?>() {}.type
        if(json != null) {
            val devices: ArrayList<DataModel> = gson.fromJson(json, listType)

            dataAdapter.setData(devices)
        }

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

    private fun getMockData(): ArrayList<DataModel.Light> = arrayListOf(
        DataModel.Light(
            name = "1",
            place = "pl",
            condition = true
        ),
        DataModel.Light(
            name = "1",
            place = "pl",
            condition = false
        ),

    )

}