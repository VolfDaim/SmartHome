package com.example.smarthome.ui.fragment_home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.smarthome.R
import com.example.smarthome.domain.LightsDataSource
import com.example.smarthome.httpclient.ApiInterface
import com.example.smarthome.ui.data.listClasses.Echo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CreateDevice : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_device, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onStart() {
        super.onStart()
    }

    companion object {
        fun newInstance() = CreateDevice()
    }
}