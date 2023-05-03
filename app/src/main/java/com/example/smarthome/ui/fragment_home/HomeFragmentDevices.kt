package com.example.smarthome.ui.fragment_home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.smarthome.R
import com.example.smarthome.domain.LightsDataSource
import com.example.smarthome.httpclient.ApiInterface
import com.example.smarthome.ui.data.listClasses.Echo
import com.example.smarthome.ui.data.listClasses.Light
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentDevices : Fragment() {

    private var recycler: RecyclerView? = null
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
        recycler = view.findViewById(R.id.rv_light)
        recycler?.adapter = HomeLightAdapter()

        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        createDevice = view.findViewById(R.id.add_device)

    }

    @SuppressLint("ResourceType")
    override fun onStart() {
        super.onStart()

        swipeRefresh?.setOnRefreshListener {

            val service = ApiInterface.create().getEcho()
            service.enqueue(object :
                Callback<Echo> {
                override fun onResponse(call: Call<Echo>, response: Response<Echo>) {
                    if (response?.body()!=null)
                        Log.i("INFO", response.body().toString())
                }

                override fun onFailure(call: Call<Echo>?, t: Throwable?) {
                    Toast.makeText(view?.context, "Response Failed", Toast.LENGTH_SHORT).show()
                }
            })
            swipeRefresh?.isRefreshing = false
        }

        createDevice?.setOnClickListener {
            val transaction = parentFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_home_frame, CreateDevice.newInstance())
                commit()
            }
        }
        updateData(LightsDataSource().getLights())
    }

    private fun updateData(devices: List<Light>) {
        (recycler?.adapter as? HomeLightAdapter)?.apply {
            bindLights(devices)
        }
    }

    private fun doOnClick(light: Light) {

    }

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(light: Light) {
            doOnClick(light)
        }
    }

    companion object {
        fun newInstance() = HomeFragmentDevices()
    }

}