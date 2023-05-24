package com.example.smarthome.ui.fragment_home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.smarthome.R
import com.example.smarthome.httpclient.ApiService
import com.example.smarthome.ui.fragment_home.bo.DataModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class HomeDevicesFragment : Fragment() {

    private var swipeRefresh: SwipeRefreshLayout? = null
    private var createDevice: FloatingActionButton? = null
    //val viewModel: HomeFragmentViewModel by viewModels()

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
        updateDataAdater()

        //viewModel.liveData.observe(viewLifecycleOwner){

       // }
        swipeRefresh = view.findViewById(R.id.swipeRefresh)
        createDevice = view.findViewById(R.id.add_device)

    }

    @SuppressLint("ResourceType")
    override fun onStart() {
        super.onStart()

        updateDataAdater()
        swipeRefresh?.setOnRefreshListener {
            val prefs = activity?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
            val token = prefs?.getString("token", null)
            val editor = prefs?.edit()

            ApiService().getLights(token!!, editor!!, requireView().context)
            ApiService().getCameras(token, editor, requireView().context)
            ApiService().getDetectors(token, editor, requireView().context)

            updateDataAdater()

            swipeRefresh?.isRefreshing = false
        }

        createDevice?.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_home_frame, CreateDeviceFragment.newInstance())
                commit()
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataAdater() {
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

        sharedPrefs?.edit()?.putString("amount_devices", devices.size.toString())?.apply()
        dataAdapter.setData(devices)
        dataAdapter.notifyDataSetChanged()
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
        fun newInstance() = HomeDevicesFragment()
    }

}